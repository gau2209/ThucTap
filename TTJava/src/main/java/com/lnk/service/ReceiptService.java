/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.service;

import com.lnk.pojo.Cart;
import java.util.Map;

/**
 *
 * @author ANHTUAN
 */
public interface ReceiptService {

    public boolean addReceipt(Map<String, Cart> carts);
}
