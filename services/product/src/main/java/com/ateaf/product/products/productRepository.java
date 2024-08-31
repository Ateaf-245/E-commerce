package com.ateaf.product.products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<Product, Integer>{

    List<Product> findAllByIdInOrderById(List<Integer> productIds);

}
