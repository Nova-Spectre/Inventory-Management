package com.shubham.dev.Productservice.controller;

import com.shubham.dev.Productservice.dto.productRequest;
import com.shubham.dev.Productservice.dto.productResponse;
import com.shubham.dev.Productservice.service.productService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class productController {

    private final productService ProductService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody productRequest productRequest){
        ProductService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<productResponse> getAllProduct(){
        return ProductService.getAllProducts();
    }




}
