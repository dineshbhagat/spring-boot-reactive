package com.dkb.springbootreactive;

import com.dkb.springbootreactive.client.ProductWebClient;
import com.dkb.springbootreactive.entity.Product;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Make sure you start application before running following test
 */
public class WebClientIntegrationTest {
    private WebClient webClient;

    @Test
    public void getAllProductIntegrationTest() {
        webClient = WebClient.builder().build();
        ProductWebClient pwc = new ProductWebClient(webClient);
        Flux<Product> products = pwc.getAllProducts().take(5).log();
        assertNotNull(products);
        Long block = products.count().block();
        assertEquals(Long.valueOf(5), block);
        assertEquals("1", products.blockFirst().getId());

    }
}
