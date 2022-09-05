package com.liem.ms.productservice.query.handler.impl;

import com.liem.ms.productservice.query.entity.ProductEntity;
import com.liem.ms.productservice.core.handler.ProductQueryHandler;
import com.liem.ms.productservice.query.queries.FindProductQuery;
import com.liem.ms.productservice.query.repository.ProductRepository;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Product query handler.
 */
@Slf4j
@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductQueryHandlerImpl implements ProductQueryHandler {

  /**
   * The Repository.
   */
  private final ProductRepository repository;

  /**
   * Find products page.
   *
   * @param query the query
   * @return the page
   */
  @Override
  @QueryHandler
  public List<ProductEntity> findProducts(final @NotNull FindProductQuery query) {
    final var entities = this.repository.findAll();
    log.info("Found products: {}", entities.size());
    return entities;
  }
}
