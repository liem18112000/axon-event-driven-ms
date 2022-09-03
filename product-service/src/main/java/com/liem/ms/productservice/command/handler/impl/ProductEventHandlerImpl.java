package com.liem.ms.productservice.command.handler.impl;

import static com.liem.ms.productservice.command.config.AppConstants.PRODUCT_GROUP;

import com.liem.ms.productservice.command.event.common.DeletedEvent;
import com.liem.ms.productservice.command.event.product.ProductCreatedEvent;
import com.liem.ms.productservice.command.event.product.ProductUpdatedEvent;
import com.liem.ms.productservice.command.handler.ProductEventHandler;
import com.liem.ms.productservice.command.mapper.ProductEntityMapper;
import com.liem.ms.productservice.query.entity.ProductEntity;
import com.liem.ms.productservice.query.repository.ProductRepository;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Product event handler.
 */
@Slf4j
@AllArgsConstructor
@Service
@ProcessingGroup(PRODUCT_GROUP)
@Transactional
public class ProductEventHandlerImpl implements ProductEventHandler {

  /**
   * The Repository.
   */
  private final ProductRepository repository;

  /**
   * The Mapper.
   */
  private final ProductEntityMapper mapper;

  /**
   * On created.
   *
   * @param event the event
   */
  @EventHandler
  @Override
  public void onCreated(final @NotNull ProductCreatedEvent event) {
    log.trace("Product handle: {}", event);
    final var entity = this.mapper.toEntity(event);
    log.info("Create entity: {}", entity);
    this.repository.save(entity);
  }

  /**
   * On updated.
   *
   * @param event the event
   */
  @EventHandler
  @Override
  public void onUpdated(final @NotNull ProductUpdatedEvent event) {
    log.trace("Product handle: {}", event);
    final var entity = getProductById(event.getId()).update(event);
    log.info("Update entity: {}", entity);
    this.repository.save(entity);
  }

  /**
   * On deleted.
   *
   * @param event the event
   */
  @EventHandler
  @Override
  public void onDeleted(final @NotNull DeletedEvent<String> event) {
    log.trace("Product handle: {}", event);
    final var entity = getProductById(event.getId());
    log.info("Delete entity: {}", entity);
    this.repository.delete(entity);
  }

  /**
   * Handle exception.
   *
   * @param exception the exception
   * @throws Exception the exception
   */
  @ExceptionHandler
  protected void handleException(
      final @NotNull Exception exception) throws Exception {
    log.error(exception.getMessage());
    throw exception;
  }

  /**
   * Gets product by id.
   *
   * @param productId the product id
   * @return the product by id
   */
  protected ProductEntity getProductById(String productId) {
    return this.repository.findById(productId)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Product is not found by id: %s", productId)));
  }
}
