package com.dkb.springbootreactive.service;

import com.dkb.springbootreactive.entity.Product;
import com.dkb.springbootreactive.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl {

    @Autowired
    ProductRepositories productRepositories;

    public Flux<Product> getAllProducts() {
        Flux<Product> productFlux = productRepositories.findAll();
        return productFlux;
//                .log("TGroup:" + Thread.currentThread().getThreadGroup().getName() + ",Thread:"+Thread.currentThread().getName()
//        );//.delayElements(Duration.ofMillis(10));
    }

    public Flux<Product> add(List<String> products) {
        List<Product> list = products.parallelStream().map(s -> new Product(s)).collect(Collectors.toList());
        return productRepositories.saveAll(list);
    }

}
