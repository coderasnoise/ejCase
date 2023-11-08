package com.emlakjet.ejcase.controller;

import com.emlakjet.ejcase.entities.BillInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.emlakjet.ejcase.entities.BillInfo;
import com.emlakjet.ejcase.service.BillInfoService;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {


    private final BillInfoService billInfoService;


    @PostMapping("/create")
    public ResponseEntity<?> createBill(@RequestBody BillInfoRequest billInfoRequest) {
        ResponseEntity<?> response = billInfoService.processBillFromData(billInfoRequest);
        return response;
    }
    @GetMapping("/all")
    public ResponseEntity<List<BillInfo>> getAllBills() {
        List<BillInfo> bills = billInfoService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
}

