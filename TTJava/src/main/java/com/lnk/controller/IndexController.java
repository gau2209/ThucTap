/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.controller;

import com.lnk.pojo.Stores;
import com.lnk.service.StoresService;
import com.lnk.service.FoodsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.hibernate.criterion.Expression.sql;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ControllerAdvice
public class IndexController {

    @Autowired
    private StoresService storesService;
    @Autowired
    private FoodsService foodsService;
    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("stores", this.storesService.getStores(params));
//        model.addAttribute("dsfood", this.machService.getFoods(params));

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int count = this.storesService.countStores();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "index"; // Thư mục "WEB-INF/views/" chứa các trang view (ví dụ: index.jsp)
    }

    @GetMapping("/stores/{storeId}")
    public String storesDetails(Model model, @PathVariable(value = "storeId") int id) {
        model.addAttribute("stores", this.storesService.getStoresById(id));
        return "detail";
    }

}
