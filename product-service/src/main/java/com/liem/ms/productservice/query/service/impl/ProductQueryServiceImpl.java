package com.liem.ms.productservice.query.service.impl;

import com.liem.ms.productservice.core.dto.ProductDto;
import com.liem.ms.productservice.query.entity.ProductEntity;
import com.liem.ms.productservice.query.mapper.ProductDtoMapper;
import com.liem.ms.productservice.query.queries.FindProductQuery;
import com.liem.ms.productservice.query.service.ProductQueryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Product query service.
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryServiceImpl implements ProductQueryService<String> {

  /**
   * The Mapper.
   */
  private final ProductDtoMapper mapper;

  /**
   * The Query gateway.
   */
  private final QueryGateway queryGateway;

  /**
   * Find products page.
   *
   * @return the page
   */
  @Override
  public List<ProductDto<String>> findProducts() {
    log.info("Send query to find product");
    final var query = new FindProductQuery();
    return this.queryGateway
        .query(query, ResponseTypes.multipleInstancesOf(ProductEntity.class))
        .join().stream().map(this.mapper::toDto).collect(Collectors.toList());
  }
}
