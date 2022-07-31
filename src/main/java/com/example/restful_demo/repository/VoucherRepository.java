package com.example.restful_demo.repository;

import com.example.restful_demo.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher,Integer> {
    Optional<Voucher> findVoucherById(int id);
}
