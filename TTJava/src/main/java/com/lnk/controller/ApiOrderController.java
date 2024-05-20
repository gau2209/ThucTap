/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.controller;

import com.lnk.pojo.Foods;
import com.lnk.pojo.OrderDetail;
import com.lnk.pojo.Orders;
import com.lnk.service.OrderService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ANHTUAN
 */
@RestController
@RequestMapping("/api")
public class ApiOrderController {
    @Autowired 
    private OrderService orderService;
    
    @RequestMapping("/order/")
    @CrossOrigin
    public ResponseEntity<List<Orders>> listOders(@RequestParam Map<String, String> params) { // passsss
        return new ResponseEntity<>(this.orderService.getListOrder(params), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/order/{orderId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Orders> detailsOrder(@PathVariable(value = "orderId") int id) {
        return new ResponseEntity<>(this.orderService.getOrderById(id), HttpStatus.OK);
    }
    
    @GetMapping("/order/{orderId}/orderdetails/")
    @CrossOrigin
    public ResponseEntity<List<OrderDetail>> listOrderDetail(@PathVariable(value = "orderId") int id) {
        return new ResponseEntity<>(this.orderService.getOrderDetail(id), HttpStatus.OK);
    }
    
}
