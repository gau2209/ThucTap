/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lnk.repository;

import com.lnk.pojo.Stores;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jackie's PC
 */
public interface StoreRepository {

    List<Stores> getStores(Map<String, String> params);

    int countStores();

    Stores addOrUpdateStores(Stores t);

    Stores getStoresById(int id);

    boolean deleteStores(int id);
}
