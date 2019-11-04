package com.dkb.springbootreactive.configuration;

import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
public class Configuration {
  @Bean
  @ConditionalOnMissingBean
  WebClient webClient() {
      return WebClient.builder().build();
  }
  
}
