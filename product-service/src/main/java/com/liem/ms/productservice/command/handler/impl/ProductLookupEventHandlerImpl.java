package com.liem.ms.productservice.command.handler.impl;

import static com.liem.ms.productservice.command.config.AppConstants.PRODUCT_GROUP;

import com.liem.ms.productservice.command.entity.ProductLookupEntity;
import com.liem.ms.productservice.command.event.common.DeletedEvent;
import com.liem.ms.productservice.command.event.product.ProductCreatedEvent;
import com.liem.ms.productservice.command.event.product.ProductUpdatedEvent;
import com.liem.ms.productservice.command.handler.ProductEventHandler;
import com.liem.ms.productservice.command.mapper.ProductLookupEntityMapper;
import com.liem.ms.productservice.command.repository.ProductLookupRepository;
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
 * The type Product lookup event handler.
 */
@Slf4j
@Service
@AllArgsConstructor
@ProcessingGroup(PRODUCT_GROUP)
@Transactional(readOnly = true)
public class ProductLookupEventHandlerImpl implements ProductEventHandler {

  /**
   * The Repository.
   */
  private final ProductLookupRepository repository;

  /**
   * The Mapper.
   */
  private final ProductLookupEntityMapper mapper;

  /**
   * On created.
   *
   * @param event the event
   */
  @Override
  @EventHandler
  public void onCreated(final @NotNull ProductCreatedEvent event) {
    log.trace("Product lookup handle: {}", event);
    final var entity = this.mapper.toEntity(event);
    log.info("Save look up entity: {}", entity);
    this.repository.save(entity);
  }

  /**
   * On updated.
   *
   * @param event the event
   */
  @Override
  @EventHandler
  public void onUpdated(ProductUpdatedEvent event) {
    log.trace("Product lookup handle: {}", event);
    final var entity = this.getLookupEntityById(event.getId())
        .updateProductName(event);
    log.info("Update look up entity: {}", entity);
    this.repository.save(entity);
  }

  /**
   * On deleted.
   *
   * @param event the event
   */
  @Override
  @EventHandler
  public void onDeleted(DeletedEvent<String> event) {
    log.trace("Product lookup handle: {}", event);
    final var entity = this.getLookupEntityById(event.getId());
    log.info("Delete look up entity: {}", entity);
    this.repository.delete(entity);
  }

  /**
   * Handle exception.
   *
   * @param exception the exception
   */
  @ExceptionHandler
  protected void handleException(Exception exception) {
    log.error(exception.getMessage());
  }

  /**
   * Gets lookup entity by id.
   *
   * @param productId the product id
   * @return the lookup entity by id
   */
  protected ProductLookupEntity getLookupEntityById(String productId) {
    return this.repository.findByProductId(productId)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Lookup entity with product id '%s' not found", productId)));
  }
}
