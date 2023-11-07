package com.emlakjet.ejcase.service;
import com.emlakjet.ejcase.entities.BillInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BillInfoService {
    ResponseEntity<?> processBillFromData(String data);

    ResponseEntity<?> processBill(BillInfo billInfo);
    public List<BillInfo> getAllBills();
}
