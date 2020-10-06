package com.codegym.repository;

import com.codegym.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
    Page<Review> findAllByProduct_id(String id);

    @Query("select avg(r.rate) from Review r")
    double getRateOfProductById(String id);

    Integer countReviewsByProduct_idAndRateEquals(String id, int star);
}
