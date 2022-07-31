package com.example.restful_demo.controller;

import com.example.restful_demo.entity.Orders;
import com.example.restful_demo.model.dto.OrderDto;
import com.example.restful_demo.payload.request.OrderRequest;
import com.example.restful_demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return ResponseEntity.ok("Create order success");
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getOrders(@PathVariable int userId){
        Set<OrderDto> list =  orderService.getOrders(userId);
        return  ResponseEntity.ok(list);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeOrder(@PathVariable int id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Delete success");
    }
    @GetMapping
    public ResponseEntity<?> getOrderById(@RequestParam int id){
        OrderDto orderDto = orderService.getOrderById(id);
        return ResponseEntity.ok(orderDto);
    }
}
