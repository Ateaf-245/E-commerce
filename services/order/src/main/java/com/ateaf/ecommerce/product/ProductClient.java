package com.ateaf.ecommerce.product;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ateaf.ecommerce.exception.BusinessException;
import com.ateaf.ecommerce.product.requests.PurchaseRequest;
import com.ateaf.ecommerce.product.responses.PurchaseResponse;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductClient {

    @Value("${application.config.product-url}")
    private final String productUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody,headers); 

        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                    productUrl + "/purchase",
                    HttpMethod.POST,
                    requestEntity,
                    responseType
                    );

        if (responseEntity.getStatusCode().isError()) {
            log.error(responseEntity.getBody().toString());

            throw new BusinessException("An Error occurred while processing the products purchase: "+ responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
