package com.example.restful_demo.service;

import com.example.restful_demo.entity.Category;
import com.example.restful_demo.entity.Product;
import com.example.restful_demo.model.dto.CategoryDto;
import com.example.restful_demo.model.mapper.Mapper;
import com.example.restful_demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceImp implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDto> getCategoryByGender(String gender) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category c : categoryRepository.getCategoryByGender(gender)){
            categoryDtos.add(Mapper.toCategoryDto(c));
        }
        return categoryDtos;
    }

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category c : categoryRepository.findAll()){
            categoryDtos.add(Mapper.toCategoryDto(c));
        }
        return categoryDtos;
    }

}
