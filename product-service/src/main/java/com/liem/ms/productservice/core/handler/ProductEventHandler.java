package com.liem.ms.productservice.core.handler;

import com.liem.ms.coreservice.events.ProductReservedEvent;
import com.liem.ms.productservice.command.event.common.DeletedEvent;
import com.liem.ms.productservice.command.event.product.ProductCreatedEvent;
import com.liem.ms.productservice.command.event.product.ProductUpdatedEvent;

/**
 * The interface Product event handler.
 */
public interface ProductEventHandler {

  /**
   * On created.
   *
   * @param event the event
   */
  void onCreated(ProductCreatedEvent event);

  /**
   * On updated.
   *
   * @param event the event
   */
  void onUpdated(ProductUpdatedEvent event);

  /**
   * On deleted.
   *
   * @param event the event
   */
  void onDeleted(DeletedEvent<String> event);

  /**
   * On reserved.
   *
   * @param event the event
   */
  void onReserved(ProductReservedEvent event);

}
