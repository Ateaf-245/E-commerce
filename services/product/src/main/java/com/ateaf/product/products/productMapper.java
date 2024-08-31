package com.ateaf.product.products;

import org.springframework.stereotype.Service;

import com.ateaf.product.Categorys.Category;
import com.ateaf.product.products.requests.ProductRequest;
import com.ateaf.product.products.responses.ProductPurchaseResponse;
import com.ateaf.product.products.responses.ProductResponse;

@Service
public class productMapper {

    public Product toProduct(ProductRequest request) {
    
        return Product.builder()
            .id(request.id())
            .name(request.name())
            .description(request.description())
            .price(request.price())
            .availableQuantity(request.availableQuantity())
            .category(
                Category.builder()
                .id(request.categoryId())
                .build())
            .build();
    }

    public ProductResponse toProductResponse(Product product){
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getAvailableQuantity(),
            product.getPrice(),
            product.getCategory().getId(),
            product.getCategory().getName(),
            product.getCategory().getDescription());
    }

    public ProductPurchaseResponse toProductPuchasedResponse(Product product,double quantity) {
      return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity);
    }

}
