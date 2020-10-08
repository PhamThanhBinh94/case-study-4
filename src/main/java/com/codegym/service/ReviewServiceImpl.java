package com.codegym.service;

import com.codegym.model.Review;
import com.codegym.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Page<Review> findAllByProductId(String id, Pageable pageable) {
        return reviewRepository.findAllByProductId(id, pageable);
    }

    @Override
    public void addReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Integer> getListRatingOfProductById(String id) {
        List<Integer> ratingList = new ArrayList<>();
            for(int i=1; i < 6; i++) {
            Integer numOfRate = reviewRepository.countReviewsByProductIdAndRateEquals(id, i);
            ratingList.add(numOfRate);
        }
        return ratingList;
    }
}

