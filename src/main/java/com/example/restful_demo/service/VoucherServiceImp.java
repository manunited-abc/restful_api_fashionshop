package com.example.restful_demo.service;

import com.example.restful_demo.entity.User;
import com.example.restful_demo.entity.Voucher;
import com.example.restful_demo.payload.request.UserVoucher;
import com.example.restful_demo.payload.response.MessageResponse;
import com.example.restful_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoucherServiceImp implements VoucherService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserVoucher getUserHasVoucher(String email) {
        User user =userRepository.findUserHasVoucher(email);
        UserVoucher userVoucher = new UserVoucher();
        if(user!=null) {
            userVoucher.setId(user.getId());
            userVoucher.setVouchers(user.getVouchers());
        }
        return userVoucher;
    }
}
