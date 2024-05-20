/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.controller;

import com.lnk.pojo.Users;
import com.lnk.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jackie's PC
 */
@Controller
public class UserController {

    @Autowired
    private UserService userDetailsService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("users", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute(value = "users") Users user) {
        String errMsg = "";

        // Trích xuất các thông tin cần thiết từ đối tượng User
        Map<String, String> params = new HashMap<>();
        params.put("firstName", user.getFirstName());
        params.put("lastName", user.getLastName());
        params.put("phone", user.getPhone());
        params.put("email", user.getEmail());
        params.put("username", user.getUsername());
        params.put("password", user.getPassword());
        params.put("address", user.getAddress());

        // Kiểm tra confirmPassword và thêm giá trị vào params
        String confirmPassword = user.getConfirmPassword();
        if (confirmPassword != null) {
            params.put("confirmPassword", confirmPassword);
        } else {
            errMsg = "Vui lòng xác nhận mật khẩu";
            model.addAttribute("errMsg", errMsg);
            return "register"; // Trả về trang đăng ký với thông báo lỗi
        }

        MultipartFile avatar = user.getFile();

        Users addUser = this.userDetailsService.addUser(params, avatar);

        if (addUser != null && addUser.getPassword().equals(confirmPassword)) {
            return "redirect:/login";
        } else {
            errMsg = "Đã có lỗi xảy ra hoặc mật khẩu không khớp";
        }

        model.addAttribute("errMsg", errMsg);
        return "login";
    }

}
