/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.service;

import com.lnk.pojo.Users;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
public interface UserService extends UserDetailsService {

    Users getUserByUn(String username);

    boolean authUser(String username, String password);

    Users addUser(Map<String, String> params, MultipartFile avatar);
    
    Users addUserStore(Map<String, String> params, MultipartFile avatar);

    Users getUsersById(int id);

}
