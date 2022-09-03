package com.liem.ms.productservice.command.config;

import com.liem.ms.productservice.command.interceptor.CreateProductCommandInterceptor;
import com.liem.ms.productservice.command.interceptor.DeleteProductCommandInterceptor;
import com.liem.ms.productservice.command.interceptor.UpdateProductCommandInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * The type Interceptor configuration.
 */
@Slf4j
@Configuration
public class InterceptorConfiguration {

  /**
   * Register interceptor.
   *
   * @param bus     the bus
   * @param context the context
   */
  @Autowired
  public void registerCreateProductCommandInterceptor(CommandBus bus, ApplicationContext context) {
    final var interceptor = context.getBean(CreateProductCommandInterceptor.class);
    log.trace("Handler registration: {}", interceptor);
    bus.registerDispatchInterceptor(interceptor);
  }

  /**
   * Register update product command interceptor.
   *
   * @param bus     the bus
   * @param context the context
   */
  @Autowired
  public void registerUpdateProductCommandInterceptor(CommandBus bus, ApplicationContext context) {
    final var interceptor = context.getBean(UpdateProductCommandInterceptor.class);
    log.trace("Handler registration: {}", interceptor);
    bus.registerDispatchInterceptor(interceptor);
  }

  /**
   * Register delete command interceptor.
   *
   * @param bus     the bus
   * @param context the context
   */
  @Autowired
  public void registerDeleteCommandInterceptor(CommandBus bus, ApplicationContext context) {
    final var interceptor = context.getBean(DeleteProductCommandInterceptor.class);
    log.trace("Handler registration: {}", interceptor);
    bus.registerDispatchInterceptor(interceptor);
  }
}
