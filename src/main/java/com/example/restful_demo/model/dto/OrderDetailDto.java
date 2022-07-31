package com.example.restful_demo.model.dto;

import com.example.restful_demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    int quantity;
    String size;
    int productId;
}
