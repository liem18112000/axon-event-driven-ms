package com.liem.ms.productservice.core.handler;

import com.liem.ms.productservice.query.entity.ProductEntity;
import com.liem.ms.productservice.query.queries.FindProductQuery;
import java.util.List;

/**
 * The interface Product query handler.
 */
public interface ProductQueryHandler {

  /**
   * Find products page.
   *
   * @param query the query
   * @return the page
   */
  List<ProductEntity> findProducts(FindProductQuery query);
}
