package com.liem.ms.orderservice.command.handler;

import com.liem.ms.orderservice.command.entity.OrderLookupEntity;
import com.liem.ms.orderservice.command.events.OrderCreatedEvent;
import com.liem.ms.orderservice.command.repository.OrderLookupRepository;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * The type Order lookup event handler.
 */
@Slf4j
@AllArgsConstructor
@Component
public class OrderLookupEventHandler {

  /**
   * The Repository.
   */
  private final OrderLookupRepository repository;

  /**
   * On order created.
   *
   * @param event the event
   */
  @EventHandler
  public void onCreated(final @NotNull OrderCreatedEvent event) {
    log.trace("Order lookup handle: {}", event);
    var entity = new OrderLookupEntity();
    entity.setId(event.getOrderId());
    entity.setQuantity(event.getQuantity());
    entity.setUserId(event.getUserId());
    entity.setProductId(event.getProductId());
    log.info("Create lookup order: {}", entity);
    this.repository.save(entity);
  }

}
