package com.daniel.microservices.product_service.service;

import com.daniel.microservices.product_service.dto.ProductResponse;
import com.daniel.microservices.product_service.model.Product;
import com.daniel.microservices.product_service.dto.ProductRequest;
import com.daniel.microservices.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = new Product();
        product.name = productRequest.name;
        product.description = productRequest.description;
        product.price = productRequest.price;

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toUnmodifiableList());
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.price)
                .description(product.description).build();
    }
}
