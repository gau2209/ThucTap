/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lnk.repository;

import com.lnk.pojo.Cart;
import java.util.Map;

/**
 *
 * @author ANHTUAN
 */
public interface ReceiptRepository {
    boolean addReceipt (Map<String,Cart>carts);
}
