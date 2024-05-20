/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lnk.repository;

import com.lnk.pojo.Reviews;
import java.util.List;

/**
 *
 * @author Jackie's PC
 */
public interface ReviewsRepository {

    List<Reviews> getReviews(int foodId);

    Reviews addReviews(Reviews c);
}
