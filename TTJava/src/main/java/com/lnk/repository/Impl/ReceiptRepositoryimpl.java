/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.repository.Impl;

import com.lnk.pojo.Cart;
import com.lnk.pojo.OrderDetail;
import com.lnk.pojo.Orders;
import com.lnk.pojo.Users;
import com.lnk.repository.FoodsRepository;
import com.lnk.repository.ReceiptRepository;
import com.lnk.repository.UserRepository;
import java.util.Date;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ANHTUAN
 */
@Repository
public class ReceiptRepositoryimpl implements ReceiptRepository {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private FoodsRepository foodsRepo;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(Map<String, Cart> carts) {
        Session s = this.factory.getObject().getCurrentSession();
        Orders order = new Orders();

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Users u = this.userRepo.getUserByUsername(authentication.getName());
            order.setUserId(u);
            order.setOrderDate(new Date());
            order.setPaymentMethod("Online");
            s.save(u);

            for (Cart c : carts.values()) {
                OrderDetail d = new OrderDetail();
                d.setFoodId(this.foodsRepo.getFoodsById(Integer.parseInt(c.getId().toString())));
                d.setOrderId(order);
                d.setNum(c.getQuantity());
                d.setUnitPrice(c.getUnitPrice());

                s.save(d);
            }
            return true;

        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}














