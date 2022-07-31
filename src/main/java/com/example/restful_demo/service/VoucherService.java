package com.example.restful_demo.service;

import com.example.restful_demo.entity.User;
import com.example.restful_demo.entity.Voucher;
import com.example.restful_demo.payload.request.UserVoucher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherService {
    public UserVoucher getUserHasVoucher(String email);
}
