package com.example.restful_demo.service;

import com.example.restful_demo.entity.Category;
import com.example.restful_demo.entity.Product;
import com.example.restful_demo.model.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<CategoryDto> getCategoryByGender(String gender);
    public List<CategoryDto> getAll();
}
