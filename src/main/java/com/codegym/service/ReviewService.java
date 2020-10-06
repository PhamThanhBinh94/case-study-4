package com.codegym.service;

import com.codegym.model.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    Page<Review> findAllByProduct_id(String id);

    void addReview(Review review);

    double getRateOfProductById(String id);

    List<Integer> getListRatingOfProductById(String id);
}
