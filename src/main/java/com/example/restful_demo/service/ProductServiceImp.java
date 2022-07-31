package com.example.restful_demo.service;

import com.example.restful_demo.entity.Category;
import com.example.restful_demo.entity.Product;

import com.example.restful_demo.model.dto.CategoryDto;
import com.example.restful_demo.model.dto.ProductDto;
import com.example.restful_demo.model.mapper.Mapper;
import com.example.restful_demo.payload.request.ProductRequest;
import com.example.restful_demo.repository.CategoryRepository;
import com.example.restful_demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductServiceImp implements  ProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<ProductDto> getAllProductWoman(int page,int numberOfProduct,String gender,int sort) {
        Pageable pageable = PageRequest.of(page, numberOfProduct);
        List<Product> productsWomans = productRepository.findProducts(pageable,gender);
        System.out.println("Number of page "+pageable.getPageNumber());
        List<ProductDto> productDtos = new ArrayList<>();
        productsWomans.forEach(product -> {
            productDtos.add(Mapper.toProduct(product));
        });

        if(sort==2){
            Collections.sort(productDtos, (ProductDto a1, ProductDto a2) -> (int)(a1.getPrice()-a2.getPrice()));
        }
        if(sort==1){
            Collections.sort(productDtos, (ProductDto a1, ProductDto a2) -> (int)(a2.getPrice()-a1.getPrice()));
        }
        return productDtos;
    }

    @Override
    public List<ProductDto> getAllProductWomanByCategory(int page, int numberOfProduct, String gender, int categoryId) {
        Pageable pageable = PageRequest.of(page, numberOfProduct);
        List<Product> productsWomans = productRepository.findProductsByCategory(pageable,gender, categoryId);
        List<ProductDto> productDtos = new ArrayList<>();
        productsWomans.forEach(product -> {
            productDtos.add(Mapper.toProduct(product));
        });

        return productDtos;
    }

    @Override
    public ProductDto getProductById(int id) {
        try{
            return Mapper.toProduct(productRepository.findProductById(id));
        }
        catch (RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException("Not found product by id: "+id);
        }
    }

    @Override
    public int countNumberOfProduct(String gender, int categoryId) {
        if(categoryId>0){
            return productRepository.numberOfProduct(gender,categoryId);
        }else{
            return productRepository.numberOfProduct(gender);
        }

    }

    @Override
    public void addProduct(ProductRequest productRequest) {
            Product product = new Product();
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setImages(productRequest.getImages());
            System.out.println(productRequest.getCategoryId());
            Category category = categoryRepository.getCategoryById(productRequest.getCategoryId());
            product.setCategory(category);
            List<Product> products = category==null?new ArrayList<>():category.getProducts();
            products.add(product);
            category.setProducts(products);
            categoryRepository.save(category);
            System.out.println("Create product success");
    }

    @Override
    public List<ProductDto> searchProduct(int page,int numberOfProduct,String search) {
        String[] split = search.split(" ");
        String lastSearch = split[split.length-1];
        List<ProductDto> productDtos = new ArrayList<>();
        Pageable pageable = PageRequest.of( page,numberOfProduct);
        if(lastSearch.equals("nam")){
            lastSearch="man";
            String searchStr = "%" + search + "%";
            List<Product> list = productRepository.searchProductsByGender(pageable,searchStr,lastSearch);

            list.forEach(product -> {
                productDtos.add(Mapper.toProduct(product));
            });

        }
        if(lastSearch.equals("nữ")) {
            lastSearch = "woman";
            String searchStr = "%" + search + "%";
            List<Product> list = productRepository.searchProductsByGender(pageable, searchStr, lastSearch);

            list.forEach(product -> {
                productDtos.add(Mapper.toProduct(product));
            });
        }else {
            if (!search.equals("")) {
                String searchStr = "%" + search + "%";
                List<Product> list = productRepository.searchProductsByName(pageable, searchStr);

                list.forEach(product -> {
                    productDtos.add(Mapper.toProduct(product));
                });
            }
        }
        return productDtos;
    }



    public void sortProduct(List<ProductDto> productDtos,int sort){
        if (sort==2){
            Collections.sort(productDtos, (ProductDto a1, ProductDto a2) -> (int)(a1.getPrice()-a2.getPrice()));
        }
        if(sort==1){
            Collections.sort(productDtos, (ProductDto a1, ProductDto a2) -> (int)(a2.getPrice()-a1.getPrice()));
        }
    }

    @Override
    public List<ProductDto> filterProduct(String gender, int categoryId, int page, int numOfProduct, int sort, int min, int max) {
        List<ProductDto> productDtos = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, numOfProduct);
        if((gender.equals("woman") || gender.equals("man")) && categoryId<0 && min<0 && max < 0){
            List<Product> products = productRepository.findProducts(pageable,gender);
            products.forEach(product -> {
                productDtos.add(Mapper.toProduct(product));
            });
            sortProduct(productDtos,sort);
        }
        if((gender.equals("woman") || gender.equals("man"))  && categoryId > 0 && min<0 && max < 0){
            List<Product> products = productRepository.findProductsByCategory(pageable,gender, categoryId);
            products.forEach(product -> {
                productDtos.add(Mapper.toProduct(product));
            });
            sortProduct(productDtos,sort);
        }
        if((gender.equals("woman") || gender.equals("man"))  && min >=0 && max >=0){
            List<Product> products = productRepository.filterPrice1(pageable,min,max,gender);
            products.forEach(product -> {
                productDtos.add(Mapper.toProduct(product));
            });
            sortProduct(productDtos,sort);
        }
        return productDtos;
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

}
