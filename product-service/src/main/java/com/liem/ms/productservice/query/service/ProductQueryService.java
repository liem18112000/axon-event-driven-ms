package com.liem.ms.productservice.query.service;

import com.liem.ms.productservice.core.dto.ProductDto;
import java.io.Serializable;
import java.util.List;

/**
 * The interface Product query service.
 *
 * @param <ID> the type parameter
 */
public interface ProductQueryService<ID extends Serializable> {

  /**
   * Find products page.
   *
   * @return the page
   */
  List<ProductDto<ID>> findProducts();

}
