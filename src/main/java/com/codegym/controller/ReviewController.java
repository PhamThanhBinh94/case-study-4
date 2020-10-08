package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.Review;
import com.codegym.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/reviews/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void getListRating(@RequestBody Review review){
        review.setDate(new Date());
        System.out.println(review);
        reviewService.addReview(review);
    }

    @RequestMapping(value = "/reviews/get-review", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Review> getReviews(@RequestBody Product product, Pageable pageable){
        return reviewService.findAllByProductId(product.getId(), pageable);
    }

    @RequestMapping(value = "/reviews/get-rate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Integer> getRates(@RequestBody Product product){
        return reviewService.getListRatingOfProductById(product.getId());
    }

}
