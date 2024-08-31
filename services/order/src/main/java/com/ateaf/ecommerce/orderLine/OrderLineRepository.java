package com.ateaf.ecommerce.orderLine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderLineRepository extends JpaRepository<OrderLine,Integer>{

    List<OrderLine> findAllByOrderId(Integer orderId);

}
