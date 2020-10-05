package com.codegym.repository;

<<<<<<< HEAD
import org.springframework.stereotype.Repository;

public interface ProductRepository {

=======
import com.codegym.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
>>>>>>> acb925d10fb7e7962aa20ff781a17c825106829b
}
