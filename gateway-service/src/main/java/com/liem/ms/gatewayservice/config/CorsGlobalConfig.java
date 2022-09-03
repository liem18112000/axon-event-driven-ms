/*
 * Copyright (c) 2021. Liem Doan
 */

package com.liem.ms.gatewayservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * The type Cors global config.
 */
@Configuration
@Profile("cors")
public class CorsGlobalConfig implements WebFluxConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
        .allowedHeaders("Access-Control-Allow-Origin", "Origin", "X-Requested-With", "Content-Type",
            "Accept",
            "X-XSRF-TOKEN", "X-CSRF-TOKEN", "Authentication", "Authorization");
    WebFluxConfigurer.super.addCorsMappings(registry);
  }
}
