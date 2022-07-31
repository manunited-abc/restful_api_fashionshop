package com.example.restful_demo.model.dto;

import com.example.restful_demo.entity.OrderDetail;
import com.example.restful_demo.entity.User;
import com.example.restful_demo.entity.Voucher;
import com.example.restful_demo.payload.response.OrderDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    int id;
    Calendar createDate;
    Set<OrderDetailResponse> orderDetails = new HashSet<>();
    double discountVoucher;
    String address;
    String phoneNum;
    String name;
    double totalBill;
}
