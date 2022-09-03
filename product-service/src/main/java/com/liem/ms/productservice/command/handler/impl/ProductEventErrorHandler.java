package com.liem.ms.productservice.command.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

/**
 * The type Product event error handler.
 */
@Slf4j
public class ProductEventErrorHandler implements ListenerInvocationErrorHandler {

  /**
   * On error.
   *
   * @param exception           the exception
   * @param eventMessage        the event message
   * @param eventMessageHandler the event message handler
   * @throws Exception the exception
   */
  @Override
  public void onError(Exception exception, EventMessage<?> eventMessage,
      EventMessageHandler eventMessageHandler) throws Exception {
    log.error("Product event error handle {} with message {} by {}",
        exception.getMessage(), eventMessage, eventMessageHandler);
    throw exception;
  }
}
