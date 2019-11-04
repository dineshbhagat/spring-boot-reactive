package com.dkb.springbootreactive.client;


import com.dkb.springbootreactive.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Ref: https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
 */
@Component
@Slf4j
public class ProductWebClient {

    public Flux<Product> getAllProducts() {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080")
                //.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

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
