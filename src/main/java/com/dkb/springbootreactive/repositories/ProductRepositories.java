package com.dkb.springbootreactive.repositories;

import com.dkb.springbootreactive.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositories extends ReactiveMongoRepository<Product, String> {
}
