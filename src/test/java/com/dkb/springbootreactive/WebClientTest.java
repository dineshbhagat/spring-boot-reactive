package com.dkb.springbootreactive;

import com.dkb.springbootreactive.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
@Slf4j
public class WebClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = webTestClient
                .mutate()
                .responseTimeout(Duration.ofMillis(36000))
                .build();
    }

    @Test
    public void testWebClient() {

        webTestClient
                .get()
                .uri("/getAll")
                //.accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());

        FluxExchangeResult<Product> str = webTestClient
                // Create a GET request to test an endpoint
                .get().uri("/getAll")
                //.accept(MediaType.APPLICATION_JSON)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .returnResult(Product.class);
        System.out.println(str.getResponseBody().doOnNext(System.out::println));

    }
}
