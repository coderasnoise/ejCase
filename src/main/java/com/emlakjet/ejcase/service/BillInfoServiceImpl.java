package com.emlakjet.ejcase.service;


import com.emlakjet.ejcase.entities.BillInfoRequest;
import com.emlakjet.ejcase.repository.BillInfoRepository;
import com.emlakjet.ejcase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.emlakjet.ejcase.entities.BillInfo;
import com.emlakjet.ejcase.entities.User;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class BillInfoServiceImpl implements BillInfoService {


    private final BillInfoRepository billInfoRepository;


    private final UserRepository userRepository;


    @Value("${credit-limit}")
    private double creditLimit;


    @Override
    public ResponseEntity<?> processBillFromData(BillInfoRequest billInfoRequest) {
        var amount = billInfoRepository.checkAmountByEmail(billInfoRequest.getEmail());
        if (isNull(amount)){
            amount = 0.0;
        }
        if( amount + billInfoRequest.getAmount() > creditLimit){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu email adresi "+ billInfoRequest.getEmail() +" için fatura limiti aşıldı.");
        }
        else{
            User existingUser = userRepository.findByEmail(billInfoRequest.getEmail());
            if (isNull(existingUser)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu email adresi için kullanıcı bulunamadı, lütfen önce kullanıcı kaydı yapınız.");
            }
            BillInfo bill =  new BillInfo();
            bill.setUser(existingUser);
            bill.setAmount(billInfoRequest.getAmount());
            bill.setProductName(billInfoRequest.getProductName());
            bill.setBillNo(billInfoRequest.getBillNo());

            BillInfo savedBill = billInfoRepository.save(bill);
        }
        return null; //mapstructtan sonra düzenlenecek.
    }

    @Override
    public List<BillInfo> getAllBills() {
        return billInfoRepository.findAll();
    }
}