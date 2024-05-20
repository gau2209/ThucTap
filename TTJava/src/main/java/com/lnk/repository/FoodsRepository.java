/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lnk.repository;

import com.lnk.pojo.Foods;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jackie's PC
 */
public interface FoodsRepository {

    List<Foods> getFoods(Map<String, String> params);

    Long countFoods();

    Foods addOrUpdateFoods(Foods f);

    Foods getFoodsById(int id);

    boolean deleteFoods(int id);
    
    List<Foods> getFoodsByStoreId(int storeId);
}
