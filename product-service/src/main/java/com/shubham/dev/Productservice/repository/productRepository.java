package com.shubham.dev.Productservice.repository;

import com.shubham.dev.Productservice.model.product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface productRepository extends MongoRepository<product,String> {
}
