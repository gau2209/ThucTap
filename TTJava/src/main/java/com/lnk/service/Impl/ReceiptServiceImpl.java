/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.service.Impl;

import com.lnk.pojo.Cart;
import com.lnk.repository.ReceiptRepository;
import com.lnk.service.ReceiptService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ANHTUAN
 */
@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepo;

    @Override
    public boolean addReceipt(Map<String, Cart> carts) {
        return this.receiptRepo.addReceipt(carts);
    }

}