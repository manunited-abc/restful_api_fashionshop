package com.example.restful_demo.service;

import com.example.restful_demo.entity.Category;
import com.example.restful_demo.entity.Product;
import com.example.restful_demo.model.dto.CategoryDto;
import com.example.restful_demo.model.dto.ProductDto;
import com.example.restful_demo.payload.request.ProductRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public interface ProductService {
    public List<ProductDto> getAllProductWoman(int page, int numberOfProduct,String gender, int sort);
    public List<ProductDto> getAllProductWomanByCategory(int page, int numberOfProduct,String gender, int categoryId);;
    public ProductDto getProductById(int id);
    public int countNumberOfProduct(String gender,int categoryId);
    public void addProduct(ProductRequest productRequest);
    public List<ProductDto> searchProduct(int page,int numOfProduct,String search);
    public List<ProductDto> filterProduct(String gender, int categoryId,int page, int numOfProduct,int sort, int min, int max);
    public void deleteProduct(int id);
}
