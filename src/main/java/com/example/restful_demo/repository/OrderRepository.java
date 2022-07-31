package com.example.restful_demo.repository;

import com.example.restful_demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query("select o from Orders o join fetch o.orderDetails where o.user.id =:id")
    Set<Orders> findOrdersByIdUser(int id);
    @Query("select o from Orders o join fetch o.orderDetails where o.id=:id")
    Orders findOrdersById(int id);
}
