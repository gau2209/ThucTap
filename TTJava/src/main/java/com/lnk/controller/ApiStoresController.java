/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.controller;

import com.lnk.pojo.Foods;
import com.lnk.pojo.Reviews;
import com.lnk.pojo.Stores;
import com.lnk.service.FoodsService;
import com.lnk.service.StoresService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jackie's PC
 */
@RestController
@RequestMapping("/api")
public class ApiStoresController {

    @Autowired
    private StoresService storesService;
    @Autowired
    private FoodsService foodSer;

    @DeleteMapping("stores/{storeId}")
    public void deleteStores(@PathVariable(value = "storeId") int id) {
        this.storesService.deleteStores(id);
    }

    @RequestMapping("/stores/")
    @CrossOrigin
    public ResponseEntity<List<Stores>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.storesService.getStores(params), HttpStatus.OK);
    }

    @RequestMapping(path = "/stores/{storeId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Stores> detailsStores(@PathVariable(value = "storeId") int id) {
        return new ResponseEntity<>(this.storesService.getStoresById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/createStores/", 
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<Stores> addStore(@RequestParam Map<String, String> params, @RequestPart MultipartFile file) {
        Stores t = this.storesService.addOrUpdateStores(params, file);
        return new ResponseEntity<>(t,HttpStatus.CREATED);
    }
    
    @GetMapping("/stores/{storeId}/foods/")
    @CrossOrigin
    public ResponseEntity<List<Foods>> listFoods(@PathVariable(value = "storeId") int id) {
        return new ResponseEntity<>(this.foodSer.getFoodsByStoreId(id), HttpStatus.OK);
    }
    
    @GetMapping("/stores/foods/")
    @CrossOrigin
    public ResponseEntity<List<Foods>> listFoodsStore(@PathVariable(value = "storeId") int id) {
        return new ResponseEntity<>(this.foodSer.getFoodsByStoreId(id), HttpStatus.OK);
    }
}