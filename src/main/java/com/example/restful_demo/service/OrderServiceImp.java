package com.example.restful_demo.service;

import com.example.restful_demo.entity.*;
import com.example.restful_demo.model.dto.OrderDetailDto;
import com.example.restful_demo.model.dto.OrderDto;
import com.example.restful_demo.model.mapper.Mapper;
import com.example.restful_demo.payload.request.OrderRequest;
import com.example.restful_demo.payload.response.OrderDetailResponse;
import com.example.restful_demo.repository.OrderRepository;
import com.example.restful_demo.repository.ProductRepository;
import com.example.restful_demo.repository.UserRepository;
import com.example.restful_demo.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderServiceImp implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    VoucherRepository voucherRepository;
    @Override
    public void createOrder(OrderRequest orderRequest) {
        User user = null;
        Voucher voucher = null;

        System.out.println(orderRequest.getVoucherId());
        if(orderRequest.getUserId()>0){
            user = userRepository.findUserById(orderRequest.getUserId()).orElse(null);
        }
        if(orderRequest.getVoucherId()>0){
            voucher = voucherRepository.findVoucherById(orderRequest.getVoucherId()).orElse(null);
        }
        Set<OrderDetailDto> orderDetailDtos = orderRequest.getOrderDetails();
        Set<OrderDetail> orderDetails = new HashSet<>();
        for(OrderDetailDto o : orderDetailDtos){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(productRepository.findProductById(o.getProductId()));
            orderDetail.setQuantity(o.getQuantity());
            orderDetail.setSize(o.getSize());
            orderDetails.add(orderDetail);
        }
        Orders order = new Orders();
        order.setCreateDate(Calendar.getInstance());
        order.setName(orderRequest.getName().equals("")?user.getName():orderRequest.getName());
        order.setPhoneNum(orderRequest.getPhoneNum().equals("")?user.getPhone():orderRequest.getPhoneNum());
        order.setAddress(orderRequest.getAddress());
        order.setOrderDetails(orderDetails);
        order.setVoucher(voucher);
        order.setTotalBill(orderRequest.getTotalPrice());
        order.setUser(user);
        orderRepository.save(order);
        System.out.println("Thêm đơn hàng thành công");
    }

    @Override
    public Set<OrderDto> getOrders(int userId) {
        Set<OrderDto> list = new HashSet<>();
        Set<Orders> orders = orderRepository.findOrdersByIdUser(userId);
        orders.forEach(order ->{
           OrderDto orderDto = new OrderDto();
           orderDto.setId(order.getId());
           orderDto.setCreateDate(order.getCreateDate());
           orderDto.setAddress(order.getAddress());
           orderDto.setName(order.getName());
           orderDto.setPhoneNum(order.getPhoneNum());
           orderDto.setTotalBill(order.getTotalBill());
           list.add(orderDto);
        });
        return list;
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
        System.out.println("Xóa đơn hàng thành công");
    }

    @Override
    public OrderDto getOrderById(int id) {
        Orders order = orderRepository.findOrdersById(id);
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreateDate(order.getCreateDate());
        orderDto.setAddress(order.getAddress());
        orderDto.setName(order.getName());
        orderDto.setPhoneNum(order.getPhoneNum());
        orderDto.setTotalBill(order.getTotalBill());
        orderDto.setDiscountVoucher(order.getVoucher()!=null? order.getVoucher().getDiscount():0);
        Set<OrderDetailResponse> orderDetails = new HashSet<>();
        order.getOrderDetails().forEach(orderDetail -> {
            OrderDetailResponse o = new OrderDetailResponse();
            o.setId(orderDetail.getId());
            o.setProduct(Mapper.toProductOrder(orderDetail.getProduct()));
            o.setQuantity(orderDetail.getQuantity());
            o.setSize(orderDetail.getSize());

            orderDetails.add(o);
        });
        orderDto.setOrderDetails(orderDetails);
        return  orderDto;
    }
}
