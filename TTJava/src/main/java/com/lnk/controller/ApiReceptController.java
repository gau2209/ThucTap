/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.controller;

import com.lnk.pojo.Cart;
import com.lnk.service.ReceiptService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ANHTUAN
 */
@RestController
@RequestMapping("/api")
public class ApiReceptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/pay/")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public void addReceipt(@RequestBody Map<String, Cart> carts) {
        this.receiptService.addReceipt(carts);
    }
}

