/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.controller;

import com.lnk.service.FoodsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jackie's PC
 */
@Controller
public class ListFoodController {
     @Autowired
    private FoodsService foodsService;

     @GetMapping("/listfood")
    public String listFood(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("foods", this.foodsService.getFoods(params));

        return "listfood";
    }
}
