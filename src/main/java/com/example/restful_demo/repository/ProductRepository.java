package com.example.restful_demo.repository;

import com.example.restful_demo.entity.Category;
import com.example.restful_demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.geom.QuadCurve2D;
import java.util.List;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
   // @Query("select p from Product p join fetch p.images join  fetch p.orderDetails inner join p.category where p.category.gender = 'Nữ'")
    @Query("select distinct p from  Product p join fetch p.images inner join p.category c where c.gender =:gender")
    List<Product> findProducts(Pageable pageable,@Param("gender") String gender);
    @Query("select distinct p from  Product p join fetch p.images inner join p.category c where c.gender =:gender and c.id =:categoryId")
    List<Product> findProductsByCategory(Pageable pageable,@Param("gender") String gender, @Param("categoryId") int categoryId );
    @Query("select distinct p from  Product p join fetch p.images where p.id =:id")
    Product findProductById(@Param("id") int id);
    @Query("select  count(p) from Product p where p.category.id=:categoryId and p.category.gender=:gender")
    int numberOfProduct(String gender,int categoryId);
   @Query("select  count(p) from Product p where  p.category.gender=:gender")
    int numberOfProduct(String gender);
    @Query("select distinct p from Product p join fetch p.images  where p.name like:search or p.category.category like:search ")
    List<Product> searchProductsByName(Pageable pageable,String search);
    @Query("select distinct p from Product p join fetch p.images  where  p.category.category like:search or p.category.gender=:gender ")
    List<Product> searchProductsByGender(Pageable pageable,String search, String gender);
    @Query("select distinct p from Product p join fetch p.images inner join p.category c where" +
            " p.price>=:min and p.price<=:max and  c.gender =:gender")
    List<Product> filterPrice1(Pageable pageable, double min, double max, String gender);
    @Query("select distinct p from Product p join fetch p.images inner join p.category c where" +
         " p.price>=:min and p.price<=:max and  c.gender =:gender and c.id =:categoryId ")
    List<Product> filterPrice2(Pageable pageable, double min, double max, String gender, int category);


}
