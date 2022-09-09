/*
 * Copyright (c) 2021-2022. Liem Doan
 */

package com.liem.ms.securityservice.config.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jackson configuration
 *
 * @see <a href="https://www.baeldung.com/spring-boot-customize-jackson-objectmapper">Customize the
 * Jackson ObjectMapper</a>
 */
@Configuration
public class JacksonConfiguration {

  /**
   * Jackson 2 object mapper builder customizer jackson 2 object mapper builder customizer.
   *
   * @return the jackson 2 object mapper builder customizer
   */
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> {
      builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
      builder.featuresToDisable(
          SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
          SerializationFeature.FAIL_ON_EMPTY_BEANS,
          DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
          DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      builder.featuresToEnable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    };
  }
}
