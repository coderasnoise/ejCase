package com.emlakjet.ejcase.service;
import com.emlakjet.ejcase.entities.BillInfo;
import com.emlakjet.ejcase.entities.BillInfoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BillInfoService {
    ResponseEntity<?> processBillFromData(BillInfoRequest billInfoRequest);
    List<BillInfo> getAllBills();
}
