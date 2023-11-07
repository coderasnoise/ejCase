package com.emlakjet.ejcase.service;


import com.emlakjet.ejcase.repository.BillInfoRepository;
import com.emlakjet.ejcase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.emlakjet.ejcase.entities.BillInfo;
import com.emlakjet.ejcase.entities.User;

import java.util.List;

@Service
public class BillInfoServiceImpl implements BillInfoService {

    @Autowired
    private BillInfoRepository billInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${credit-limit}")
    private double creditLimit;

    @Override
    public ResponseEntity<?> processBillFromData(String data) {
        String[] requestData = data.split(",");
        if (requestData.length != 6) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Geçersiz veri formatı.");
        }

        String firstName = requestData[0];
        String lastName = requestData[1];
        String email = requestData[2];
        double amount = Double.parseDouble(requestData[3]);
        String productName = requestData[4];
        String billNo = requestData[5];

        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kullanıcı bulunamadı.");
        }

        BillInfo billInfo = new BillInfo();
        billInfo.setUser(user);
        billInfo.setAmount(amount);
        billInfo.setProductName(productName);
        billInfo.setBillNo(billNo);

        return processBill(billInfo);
    }

    @Override
    public ResponseEntity<?> processBill(BillInfo billInfo) {
        double totalAmount = calculateTotalAmountForUser(billInfo.getUser());

        if (totalAmount + billInfo.getAmount() > creditLimit) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Fatura kabul edilemedi. Kredi limiti aşıldı.");
        } else {
            billInfoRepository.save(billInfo);
            return ResponseEntity.status(HttpStatus.OK).body("Fatura kabul edildi.");
        }
    }

    @Override
    public List<BillInfo> getAllBills() {
        return billInfoRepository.findAll();
    }

    private double calculateTotalAmountForUser(User user) {
        List<BillInfo> userBills = billInfoRepository.findByUser(user);
        return userBills.stream().mapToDouble(BillInfo::getAmount).sum();
    }
}