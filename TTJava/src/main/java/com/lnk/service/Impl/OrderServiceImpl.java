/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.service.Impl;

import com.lnk.pojo.OrderDetail;
import com.lnk.pojo.Orders;
import com.lnk.pojo.Users;
import com.lnk.repository.OrderRepository;
import com.lnk.service.OrderService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ANHTUAN
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository OrderRepo;

    @Override
    public Orders getOrderById(int orderId) {
        return this.OrderRepo.getOrderById(orderId);
    }

    @Override
    public List<OrderDetail> getOrderDetail(int orderId) {
        return this.OrderRepo.getOrderDetail(orderId);
    }

    @Override
    public List<Orders> getListOrder(Map<String, String> params) {
        return this.OrderRepo.getListOrder(params);
    }

}
