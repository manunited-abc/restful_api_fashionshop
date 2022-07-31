package com.example.restful_demo.service;

import com.example.restful_demo.model.dto.OrderDto;
import com.example.restful_demo.payload.request.OrderRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface OrderService {
    public void createOrder(OrderRequest orderRequest);
    public Set<OrderDto> getOrders(int userId);
    public void deleteOrder(int id);
    public OrderDto getOrderById(int id);
}
