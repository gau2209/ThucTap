/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.repository.Impl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.lnk.pojo.Users;
import com.lnk.repository.UserRepository;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public Users getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Users WHERE username=:un");
        q.setParameter("un", username);

        return (Users) q.getSingleResult();
    }

    @Override
    public boolean authUser(String username, String password) {
        Users u = this.getUserByUsername(username);

        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public Users addUser(Users u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(u);
        return u;
    }

    @Override
    public Users getUsersById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Users.class, id);
    }

    @Override
    public Users addUserStore(Users user) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(user);
        return user;
    }

}
