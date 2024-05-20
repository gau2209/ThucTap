/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.service.Impl;

import com.lnk.pojo.Reviews;
import com.lnk.pojo.Users;
import com.lnk.repository.ReviewsRepository;
import com.lnk.repository.UserRepository;
import com.lnk.service.ReviewsService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jackie's PC
 */
@Service
public class ReviewsServiceImpl implements ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepo;
    @Autowired
    private UserRepository userRepo;

    @Override
    public Reviews addReviews(Reviews reviews) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users u = this.userRepo.getUserByUsername(authentication.getName());
        reviews.setUserId(u);
        reviews.setReviewDate(new Date());
        return this.reviewsRepo.addReviews(reviews);
    }

    @Override
    public List<Reviews> getReviews(int foodId) {
        return this.reviewsRepo.getReviews(foodId);
    }
}
