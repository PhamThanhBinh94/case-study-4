package com.codegym.repository;

import com.codegym.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
    Page<Review> findAllByProductId(String id, Pageable pageable);

//    @Query("select sum(r.rate) from Review r")
//    int getRateOfProductById(String id);

    Integer countReviewsByProductIdAndRateEquals(String id, int star);
}
