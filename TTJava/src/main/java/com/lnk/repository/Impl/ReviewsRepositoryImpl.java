/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.repository.Impl;

import com.lnk.pojo.Reviews;
import com.lnk.repository.ReviewsRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jackie's PC
 */
@Repository
@Transactional
public class ReviewsRepositoryImpl implements ReviewsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Reviews> getReviews(int foodId) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Reviews> query = builder.createQuery(Reviews.class);
        Root root = query.from(Reviews.class);

        query = query.where(builder.equal(root.get("foodId"), foodId));
//        query = query.orderBy(builder.desc(root.get("id")));

        Query q = s.createQuery(query);
//        int max = 5;
//        q.setMaxResults(max);
//
//        q.setParameter("id", foodId);
        return q.getResultList();
    }

    @Override
    public Reviews addReviews(Reviews c) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(c);
            return c;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
