package com.dkb.springbootreactive.client;


import com.dkb.springbootreactive.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

/**
 * Ref: https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
 */
@Component
@Slf4j
public class ProductWebClient {
    
    private WebClient webClient;

    public ProductWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    
    public Flux<Product> getAllProducts() {
        
        return webClient
                .get()
                .uri("/getAll")
//                .accept(MediaType.TEXT_EVENT_STREAM) //todo on setting this, request does not work, check
                .retrieve()
                .bodyToFlux(Product.class)
                .doOnComplete(() -> System.out.println("Completed GET request"))
                .retryBackoff(3, Duration.ofSeconds(1), Duration.ofSeconds(10))
                .doOnError(IOException.class, e -> log.error("Webclient error"))
                .log();
    }
}
