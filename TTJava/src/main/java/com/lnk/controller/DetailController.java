/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.controller;

import com.lnk.service.FoodsService;
import com.lnk.service.StoresService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jackie's PC
 */
@Controller
@ControllerAdvice
public class DetailController {
      @Autowired
    private StoresService storesService;
    @Autowired
    private FoodsService foodsService;

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("stores", this.storesService.getStores(params));
                model.addAttribute("foods", this.foodsService.getFoods(params));

        return "detail";
    }
}
