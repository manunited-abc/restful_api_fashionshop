package com.example.restful_demo.model.dto;

import com.example.restful_demo.entity.Category;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
public class ProductDto {
    int id;
    String name;
    String description;
    double price;
    int categoryId;
    Set<String> images = new HashSet<>();
}
