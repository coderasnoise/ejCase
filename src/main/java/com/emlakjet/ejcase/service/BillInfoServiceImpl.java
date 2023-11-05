package com.emlakjet.ejcase.service;

import com.emlakjet.ejcase.entities.UserResponse;
import com.emlakjet.ejcase.repository.BillInfoRepository;
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
    @Value("${credit-limit}")
    private double creditLimit;
    @Autowired
    private BillInfoRepository billInfoRepository;
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


    public List<BillInfo> getAllBills() {
        List<BillInfo> bills = billInfoRepository.findAll();
        return (List<BillInfo>) bills.stream()
                .collect(Collectors.toList());
    }

    private double calculateTotalAmountForUser(User user) {
        return 0;
    }
}
