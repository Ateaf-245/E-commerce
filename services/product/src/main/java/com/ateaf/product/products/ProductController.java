package com.ateaf.product.products;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ateaf.product.products.requests.ProductPurchaseRequest;
import com.ateaf.product.products.requests.ProductRequest;
import com.ateaf.product.products.responses.ProductPurchaseResponse;
import com.ateaf.product.products.responses.ProductResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
        @RequestBody @Valid ProductRequest request
    ){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping("purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> productPurchase(
        @RequestBody List<ProductPurchaseRequest> requests
    ){
        return ResponseEntity.ok(service.purchaseProducts(requests));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> getProductById(
        @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(service.getProductById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return ResponseEntity.ok(service.getProducts());
    }
}
