package com.liem.ms.orderservice.saga.config;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.Configuration;
import org.axonframework.config.ConfigurationScopeAwareProvider;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.SimpleDeadlineManager;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * The type Deadline manager configuration.
 */
@Slf4j
@org.springframework.context.annotation.Configuration
public class DeadlineManagerConfiguration {

  /**
   * The constant DEFAULT_DEADLINE_MANAGER.
   */
  public static final String DEFAULT_DEADLINE_MANAGER = "default-deadline-manager";

  /**
   * The constant PAYMENT_PROCESS_DEADLINE.
   */
  public static final String PAYMENT_PROCESS_DEADLINE = "payment-process-deadline";

  /**
   * Simple deadline manager.
   *
   * @param configuration      the configuration
   * @param transactionManager the transaction manager
   * @return the deadline manager
   */
  @Primary
  @Bean(DEFAULT_DEADLINE_MANAGER)
  public DeadlineManager simpleDeadlineManager(
      Configuration configuration, SpringTransactionManager transactionManager) {
    return SimpleDeadlineManager.builder()
        .transactionManager(transactionManager)
        .scopeAwareProvider(new ConfigurationScopeAwareProvider(configuration))
        .build();
  }
}
