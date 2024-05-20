/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.repository.Impl;
import com.lnk.pojo.Foods;
import com.lnk.repository.FoodsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jackie's PC
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class FoodsRepositoryImpl implements FoodsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Foods> getFoods(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Foods> q = b.createQuery(Foods.class);
        Root root = q.from(Foods.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("foodId")));

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

    @Override
    public Long countFoods() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT Count(*) FROM Foods");

        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public Foods addOrUpdateFoods(Foods f) {
        Session s = this.factory.getObject().getCurrentSession();
        if (f.getFoodId() == null) {
            s.save(f);
        } else {
            s.update(f);
        }
        return f;
    }

    @Override
    public Foods getFoodsById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(Foods.class, id);
    }

    @Override
    public boolean deleteFoods(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Foods f = this.getFoodsById(id);
        try {
            session.delete(f);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Foods> getFoodsByStoreId(int storeId) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Foods> query = builder.createQuery(Foods.class);
        Root root = query.from(Foods.class);

        query = query.where(builder.equal(root.get("storeId"), storeId));
//        query = query.orderBy(builder.desc(root.get("id")));

        Query q = s.createQuery(query);
//        int max = 5;
//        q.setMaxResults(max);
//
//        q.setParameter("id", foodId);
        return q.getResultList();
    }
}
