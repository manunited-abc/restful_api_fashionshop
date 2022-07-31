package com.example.restful_demo.repository;

import com.example.restful_demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> getCategoryByGender(String gender);
    List<Category> findAll();
    @Query("select distinct c from Category c  where c.id =:id")
    Category getCategoryById(@Param("id") int id);
}
