package com.emlakjet.ejcase.service;

import com.emlakjet.ejcase.entities.UserResponse;
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
import java.util.stream.Collectors;

@Service
public class BillInfoServiceImpl implements BillInfoService {
    @Autowired
    private BillInfoRepository billInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${credit-limit}")
    private double creditLimit;

    @Override
    public ResponseEntity<?> processBill(BillInfo billInfo) {
        double totalAmount = calculateTotalAmountForUser(billInfo.getUser());

        if (totalAmount + billInfo.getAmount() > creditLimit) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Fatura kabul edilemedi. Kredi limiti aşıldı.");
        } else {

            billInfoRepository.save(billInfo);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Fatura kabul edildi.");
        }
    }

    private double calculateTotalAmountForUser(User user) {

        List<BillInfo> userBills = billInfoRepository.findByUser(user);
        double totalAmount = userBills.stream().mapToDouble(BillInfo::getAmount).sum();

        return totalAmount;
    }



    public List<BillInfo> getAllBills() {
        List<BillInfo> bills = billInfoRepository.findAll();
        return (List<BillInfo>) bills.stream()
                .collect(Collectors.toList());
    }
}
