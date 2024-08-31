package com.ateaf.ecommerce.orderLine;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ateaf.ecommerce.orderLine.requests.OrderLineRequest;
import com.ateaf.ecommerce.orderLine.responses.OrderLineResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest){
        var order = mapper.toOrderLine(orderLineRequest);
        return repository.save(order).getId();

    }

    public List<OrderLineResponse> findById(Integer orderId) {
      return repository.findAllByOrderId(orderId)
            .stream()
            .map(mapper::toOrderLineResponse)
            .collect(Collectors.toList());
    }
}
