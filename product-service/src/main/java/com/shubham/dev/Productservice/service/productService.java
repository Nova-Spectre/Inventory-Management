package com.shubham.dev.Productservice.service;

import com.shubham.dev.Productservice.dto.productRequest;
import com.shubham.dev.Productservice.dto.productResponse;
import com.shubham.dev.Productservice.model.product;
import com.shubham.dev.Productservice.repository.productRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class productService {
    private final productRepository ProductRepository;


    public void createProduct(productRequest productRequest){
        product item= product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        ProductRepository.save(item);
        log.info("Product "+item.getId()+ "is saved");
    }

    public List<productResponse> getAllProducts() {
        List<product> products=ProductRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private productResponse mapToProductResponse(product Product) {
        return productResponse.builder()
                .id(Product.getId())
                .name(Product.getName())
                .description(Product.getDescription())
                .price(Product.getPrice())
                .build();
    }
}
