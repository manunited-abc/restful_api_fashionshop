package com.example.restful_demo.controller;

import com.example.restful_demo.entity.Product;
import com.example.restful_demo.model.dto.CategoryDto;
import com.example.restful_demo.model.dto.ProductDto;
import com.example.restful_demo.payload.request.ProductRequest;
import com.example.restful_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/{gender}/{categoryId}")
    public ResponseEntity<?> getProducts(@PathVariable String gender,@PathVariable int categoryId, @RequestParam int page,
                                         @RequestParam int numOfProduct,@RequestParam int sort, @RequestParam int min, @RequestParam int max){
        List<ProductDto> list = productService.filterProduct(gender,categoryId,page,numOfProduct,sort,min,max);
        return ResponseEntity.ok(list);
    }
//    @GetMapping("/{gender}/{categoryId}")
//    public ResponseEntity<?> getProductsByCategory(@PathVariable String gender,@PathVariable int categoryId, @RequestParam int page, @RequestParam int numOfProduct){
//        List<ProductDto> list = productService.getAllProductWomanByCategory(page,numOfProduct,gender,categoryId);
//        return ResponseEntity.ok(list);
//    }
    @GetMapping("/product-details/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){
        ProductDto productDto = productService.getProductById(id);
        return ResponseEntity.ok(productDto);
    }
    @GetMapping("/number-product/{gender}/{categoryId}")
    public int getNumberOfProduct(@PathVariable String gender,@PathVariable int categoryId){
        return productService.countNumberOfProduct(gender,categoryId);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest){
        productService.addProduct(productRequest);
        return ResponseEntity.ok(productRequest);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam String search,@RequestParam int page,@RequestParam int numOfProduct){
        List<ProductDto> list = productService.searchProduct(page,numOfProduct,search);
       return ResponseEntity.ok(list);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestParam int id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Delete OK");
    }





}
