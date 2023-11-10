package com.emlakjet.ejcase.service;

import com.emlakjet.ejcase.entities.BillInfo;
import com.emlakjet.ejcase.entities.User;
import com.emlakjet.ejcase.mapper.BillMapper;
import com.emlakjet.ejcase.model.bill.BillInfoRequest;
import com.emlakjet.ejcase.model.bill.BillInfoResponse;
import com.emlakjet.ejcase.repository.BillInfoRepository;
import com.emlakjet.ejcase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class BillInfoServiceImpl implements BillInfoService {

    private final BillInfoRepository billInfoRepository;
    private final UserRepository userRepository;
    private final BillMapper billMapper;

    @Value("${credit-limit}")
    private double creditLimit;


    @Override
    public ResponseEntity<?> processBillFromData(BillInfoRequest billInfoRequest) {

        User existingUser = userRepository.findByEmail(billInfoRequest.getEmail());

        if (isNull(existingUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu email adresi için kullanıcı bulunamadı, lütfen önce kullanıcı kaydı yapınız.");

        }

        var amount = billInfoRepository.checkTotalAmountByUserId(existingUser.getId());

        if (isNull(amount)) {
            amount = 0.0;
        }

        if (amount + billInfoRequest.getAmount() > creditLimit) {

            BillInfo deniedBill = billMapper.billInfoRequestToBillInfo(billInfoRequest);
            deniedBill.setUser(existingUser);
            deniedBill.setDenied(true);
            billInfoRepository.save(deniedBill);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(existingUser.getEmail() + " Bu email adresi için fatura limiti aşılmıştır.");

        } else {

            BillInfo approvedBill = billMapper.billInfoRequestToBillInfo(billInfoRequest);
            approvedBill.setUser(existingUser);
            BillInfo savedBill = billInfoRepository.save(approvedBill);
            BillInfoResponse billInfoResponse = billMapper.billInfoEntityToBillInfoResponse(savedBill);

            return new ResponseEntity<>(billInfoResponse, HttpStatus.CREATED);
        }
    }

    @Override
    public List<BillInfo> getAllBills() {
        return billInfoRepository.findAll();
    }

    @Override
    public List<BillInfo> getDeniedBills() {
        return billInfoRepository.findByIsDeniedTrue();
    }

    @Override
    public List<BillInfo> getApprovedBills() {
        return billInfoRepository.findByIsDeniedFalse();
    }
}