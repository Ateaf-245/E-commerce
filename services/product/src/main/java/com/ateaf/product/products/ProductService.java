package com.ateaf.product.products;

import com.ateaf.product.exception.ProductPurchaseException;
import com.ateaf.product.products.requests.ProductPurchaseRequest;
import com.ateaf.product.products.requests.ProductRequest;
import com.ateaf.product.products.responses.ProductPurchaseResponse;
import com.ateaf.product.products.responses.ProductResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    
    private final productRepository repository;
    private final productMapper mapper;
    
    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        // listed all the ID of the product selected by the user.
        var productIds = requests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        
        //fetched a list of products based on the product id.
        var storedProducts = repository.findAllByIdInOrderById(productIds);

        // if both list length does not match error will be thrown
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or More Product Does not Exist");
        }

        //once the above condition return true, Product ID will be sorted
        var storedRequest = requests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for(int i = 0; i < storedProducts.size(); i++){
            var product  = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            
            // check if we have enough quantity of the perticular product.
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock for product with ID: "+product.getId());
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPuchasedResponse(product,productRequest.quantity()));
        }

        return purchasedProducts;
       
    }

    public ProductResponse getProductById(Integer productId) {
       return repository.findById(productId)
            .map(mapper::toProductResponse)
            .orElseThrow(()-> new EntityNotFoundException("Product not found withthe ID : " + productId)
       );
    }

    public List<ProductResponse> getProducts() {
      return repository.findAll()
            .stream()
            .map(mapper::toProductResponse)
            .collect(Collectors.toList());
    } 
}
