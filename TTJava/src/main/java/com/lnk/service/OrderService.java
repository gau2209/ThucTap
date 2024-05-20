/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lnk.service;

import com.lnk.pojo.Foods;
import com.lnk.pojo.OrderDetail;
import com.lnk.pojo.Orders;
import com.lnk.pojo.Users;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ANHTUAN
 */
public interface OrderService {
    
    Orders getOrderById(int orderId);
    
    List<OrderDetail> getOrderDetail(int orderId);
    
    List<Orders> getListOrder(Map<String, String> params);
    
}
