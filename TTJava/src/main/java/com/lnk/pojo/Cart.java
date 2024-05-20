package com.lnk.pojo;

import lombok.Data;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ANHTUAN
 */
@Data
public class Cart {
    private Integer id;
    private String name;
    private Long unitPrice;
    private Integer quantity;
}
