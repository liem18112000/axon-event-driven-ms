package com.liem.ms.productservice.command.config;

import static com.liem.ms.productservice.core.config.AppConstants.PRODUCT_GROUP;

import com.liem.ms.productservice.command.handler.impl.ProductEventErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * The type Handler configuration.
 */
@Slf4j
@Configuration
public class HandlerConfiguration {

  /**
   * Configure.
   *
   * @param configurer the configurer
   */
  @Autowired
  public void configure(EventProcessingConfigurer configurer) {
    final var errorHandler = new ProductEventErrorHandler();
    log.trace("Handler registration: {}", errorHandler);
    configurer.registerListenerInvocationErrorHandler(PRODUCT_GROUP, configuration -> errorHandler);
  }

}
