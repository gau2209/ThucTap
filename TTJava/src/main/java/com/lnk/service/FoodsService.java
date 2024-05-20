/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lnk.service;

import com.lnk.pojo.Foods;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jackie's PC
 */
public interface FoodsService {

    List<Foods> getFoods(Map<String, String> params);

    Long countFoods();

    Foods addOrUpdateFoods(Map<String, String> params, MultipartFile file);

    Foods getFoodsById(int id);

    boolean deleteFoods(int id);

    List<Foods> getFoodsByStoreId(int storeId);
}
