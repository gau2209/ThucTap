/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lnk.service;

import com.lnk.pojo.Stores;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jackie's PC
 */
public interface StoresService {

    List<Stores> getStores(Map<String, String> params);

    int countStores();

    Stores addOrUpdateStores(Map<String, String> params, MultipartFile file);

    Stores getStoresById(int id);

    boolean deleteStores(int id);
}
