//package com.codegym.service;
//
//import com.codegym.model.Review;
//import com.codegym.repository.ReviewRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ReviewServiceImpl implements ReviewService {
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Override
//    public Page<Review> findAllByProduct_id(String id) {
//        return reviewRepository.findAllByProduct_id(id);
//    }
//
//    @Override
//    public void addReview(Review review) {
//        reviewRepository.save(review);
//    }
//
//    @Override
//    public double getRateOfProductById(String id) {
//        return reviewRepository.getRateOfProductById(id);
//    }
//
//    @Override
//    public List<Integer> getListRatingOfProductById(String id) {
//        List<Integer> ratingList = new ArrayList<>();
//        Integer sumOfRating = 0;
//        for(int i=1; i < 6; i++) {
//            Integer numOfRate = reviewRepository.countReviewsByProduct_idAndRateEquals(id, i);
//            ratingList.add(numOfRate);
//            sumOfRating += numOfRate;
//        }
//        ratingList.add(sumOfRating);
//        return ratingList;
//    }
//
//
//}
