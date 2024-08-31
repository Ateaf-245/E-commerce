package com.ateaf.ecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositry extends JpaRepository<Order,Integer> {

}
