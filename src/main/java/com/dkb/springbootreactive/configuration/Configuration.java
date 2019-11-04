package com.dkb.springbootreactive.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootConfiguration
public class Configuration {
  @Bean
  @ConditionalOnMissingBean
  WebClient webClient() {
      return WebClient.builder().build();
  }
  
}
