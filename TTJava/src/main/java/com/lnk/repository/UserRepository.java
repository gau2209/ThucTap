/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.repository;

import com.lnk.pojo.Foods;
import com.lnk.pojo.Users;

/**
 *
 * @author ADMIN
 */
public interface UserRepository {

    Users getUserByUsername(String username);

    boolean authUser(String username, String password);

    Users addUser(Users user);
    
    Users addUserStore(Users user);
    
    Users getUsersById(int id);
}
