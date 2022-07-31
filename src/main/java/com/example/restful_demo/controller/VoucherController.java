package com.example.restful_demo.controller;

import com.example.restful_demo.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/voucher")
public class VoucherController {
    @Autowired
    VoucherService voucherService;
    @GetMapping("/{email}")
    public ResponseEntity<?> getVoucher(@PathVariable String email){
        return ResponseEntity.ok(voucherService.getUserHasVoucher(email));
    }
}
