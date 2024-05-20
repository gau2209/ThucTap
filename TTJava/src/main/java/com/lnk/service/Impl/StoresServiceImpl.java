/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lnk.pojo.Stores;
import com.lnk.repository.StoreRepository;
import com.lnk.service.StoresService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jackie's PC
 */
@Service
public class StoresServiceImpl implements StoresService {

    @Autowired
    private StoreRepository storesRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Stores> getStores(Map<String, String> params) {
        return this.storesRepo.getStores(params);
    }

    @Override
    public Stores addOrUpdateStores(Map<String, String> params, MultipartFile file) {
        Stores t = new Stores();
        try {
            t.setName(params.get("name"));
            t.setLocation(params.get("location"));
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        if (!file.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                t.setImgfoodstore(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(StoresServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.storesRepo.addOrUpdateStores(t);
    }

    @Override
    public Stores getStoresById(int id) {
        return this.storesRepo.getStoresById(id);
    }

    @Override
    public boolean deleteStores(int id
    ) {
        return this.storesRepo.deleteStores(id);
    }

    @Override
    public int countStores() {
        return this.storesRepo.countStores();
    }

}
