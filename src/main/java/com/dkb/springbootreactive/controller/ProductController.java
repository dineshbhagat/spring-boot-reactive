package com.dkb.springbootreactive.controller;

import com.dkb.springbootreactive.client.ProductWebClient;
import com.dkb.springbootreactive.entity.Product;
import com.dkb.springbootreactive.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    ProductWebClient productWebClient;

    @GetMapping(path = "/getAll", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getAllProducts() {
        Flux<Product> allProducts = productService.getAllProducts();
        return allProducts;
    }

    @GetMapping(path = "/products")
    public Flux<Product> getAllProductsUsingClient() {
        Flux<Product> allProducts = productWebClient.getAllProducts();
        return allProducts;
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Product> add(@RequestBody List<String> products) {
        return productService.add(products);
    }
}
