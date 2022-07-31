package com.example.restful_demo.controller;

import com.example.restful_demo.entity.Category;
import com.example.restful_demo.entity.Product;
import com.example.restful_demo.model.dto.CategoryDto;
import com.example.restful_demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/category")
@Transactional
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/{gender}")
    public ResponseEntity<?> getCategory(@PathVariable String gender){
        List<CategoryDto> list = categoryService.getCategoryByGender(gender);
        return  ResponseEntity.ok(list);
    }

    @GetMapping()
    public ResponseEntity<?> getAllCategory(){
        List<CategoryDto> list = categoryService.getAll();
        return  ResponseEntity.ok(list);
    }
}
