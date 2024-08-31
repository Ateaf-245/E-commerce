package com.ateaf.ecommerce.customer;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.ateaf.ecommerce.exception.CustomerNotFoundException;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer =  repository.save(mapper.toCustomer(request));
        log.info("inserting new customer with name :: {}",customer.getFirstname());
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {

        var customer = repository.findById(request.id())
            .orElseThrow(() -> new CustomerNotFoundException(
                String.format("Cannot update customer:: No customer found with provided ID:: %s",request.id())
            ));
        mergeCustomer(customer, request);
        log.info("updating customer entry :: {}",customer.getId());
        repository.save(customer);
            
    }

    private void mergeCustomer(Customer customer, CustomerRequest request){
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null ){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean exitsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                    String.format("No customer found with provided ID:: %s", customerId)
                ));
    }

    public void deleteById(String customerId) {
        log.debug("Deleting customer with Id :: {}",customerId);
        repository.deleteById(customerId);
    }
}
