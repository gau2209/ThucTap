/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.repository.Impl;

import com.lnk.pojo.OrderDetail;
import com.lnk.pojo.Orders;
import com.lnk.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ANHTUAN
 */
@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public Orders getOrderById(int orderId) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Orders.class, orderId);
    }

    @Override
    public List<OrderDetail> getOrderDetail(int orderId) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<OrderDetail> query = builder.createQuery(OrderDetail.class);
        Root root = query.from(OrderDetail.class);

        query = query.where(builder.equal(root.get("orderId"), orderId));      

        Query q = s.createQuery(query);

        return q.getResultList();
    }

    @Override
    public List<Orders> getListOrder(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Orders> q = b.createQuery(Orders.class);
        Root root = q.from(Orders.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("orderId")));

        Query query = session.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                query.setMaxResults(pageSize);
                query.setFirstResult((p - 1) * pageSize);
            }
        }
        return query.getResultList();

    }

}
