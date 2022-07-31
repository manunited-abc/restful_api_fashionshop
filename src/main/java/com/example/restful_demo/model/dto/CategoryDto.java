package com.example.restful_demo.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
public class CategoryDto {
    int id;
    String category;
    String gender;
}
