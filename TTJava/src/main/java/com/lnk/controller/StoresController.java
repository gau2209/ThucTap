/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.controller;
import com.cloudinary.Cloudinary;
import com.lnk.pojo.Stores;
import com.lnk.service.StoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Jackie's PC
 */
@Controller
@ControllerAdvice
@RequestMapping("/store_admin")
public class StoresController {

    @Autowired
    private StoresService storesService;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/stores")
    public String list(Model model) {
        model.addAttribute("stores", new Stores());
        return "stores";
    }

    @GetMapping("/store/{storeId}")
    public String update(Model model, @PathVariable(value = "storeId") int id) {
        model.addAttribute("stores", this.storesService.getStoresById(id));
        return "stores";

    }
//     @PostMapping("/stores")
//     public String add(@ModelAttribute(value="stores") @Valid Stores t,BindingResult rs){
//         try {
//             if(this.storesService.addOrUpdateStores(t)){
//             Map r =this.cloudinary.uploader().upload(t.getFile().getBytes(),ObjectUtils.asMap("resource_type","auto"));
//             String img=(String) r.get("secure_url");
//             return "redirect:/";
//             }
//         } catch (IOException ex) {
//            System.err.println("== ADD STORES =="+ex.getMessage());
//         }
//                 
//         return "stores";
//         
//     }
}
