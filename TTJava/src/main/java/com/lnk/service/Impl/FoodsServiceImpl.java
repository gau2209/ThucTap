/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lnk.pojo.Foods;
import com.lnk.pojo.Stores;
import com.lnk.repository.FoodsRepository;
import com.lnk.repository.StoreRepository;
import com.lnk.service.FoodsService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jackie's PC
 */
@Service
public class FoodsServiceImpl implements FoodsService {

    @Autowired
    private StoreRepository stoRepo;
    @Autowired
    private FoodsRepository foodsRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Foods> getFoods(Map<String, String> params) {
        return this.foodsRepo.getFoods(params);
    }

    @Override
    public Long countFoods() {
        return this.foodsRepo.countFoods();
    }

    @Override
    public Foods addOrUpdateFoods(Map<String, String> params, MultipartFile file) {
        Foods f = new Foods();
        f.setName(params.get("name"));
        f.setPrice(Long.parseLong(params.get("price")));
        f.setFoodType(params.get("foodType"));
        f.setStatus(params.get("status"));
        try {
            Stores sto = this.stoRepo.getStoresById(Integer.parseInt(params.get("storeId")));
            f.setStoreId(sto);
        } catch (NumberFormatException e) {
        }
            System.out.println("Khong the convert");
        if (!file.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                f.setImgfood(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(FoodsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.foodsRepo.addOrUpdateFoods(f);
    }

    @Override
    public Foods getFoodsById(int id) {
        return this.foodsRepo.getFoodsById(id);
    }

    @Override
    public boolean deleteFoods(int id) {
        return this.foodsRepo.deleteFoods(id);
    }

    @Override
    public List<Foods> getFoodsByStoreId(int storeId) {
        return this.foodsRepo.getFoodsByStoreId(storeId);
    }
}
