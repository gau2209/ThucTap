/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.controller;

import com.lnk.pojo.Foods;
import com.lnk.service.FoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Jackie's PC
 */
@Controller
@RequestMapping("store_admin")
public class FoodsController {

    @Autowired
    private FoodsService foodsService;

    @GetMapping("/foods")
    public String list(Model model) {
        model.addAttribute("foods", new Foods());
        return "foods";
    }

    @GetMapping("/foods/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("foods", this.foodsService.getFoodsById(id));
        return "listfood";
    }
//
//    @PostMapping("/foods")
//    public String add(@ModelAttribute(value = "foods") @Valid Foods f,
//            BindingResult rs) {
//        if (!rs.hasErrors()) {
//            if (foodsService.addOrUpdateFoods(f) == true) {
//                return "redirect:/listfood";
//            }
//        }
//
//        return "listfoods";
//    }
}
