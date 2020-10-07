package com.codegym.service;

import com.codegym.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    Page<Review> findAllByProductId(String id, Pageable pageable);

    void addReview(Review review);

    List<Integer> getListRatingOfProductById(String id);
}
