package com.example.restful_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Calendar createDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<OrderDetail> orderDetails = new HashSet<>();
    @ManyToOne
    User user;
    @ManyToOne
    Voucher voucher;
    String address;
    String phoneNum;
    String name;
    double totalBill;

}
