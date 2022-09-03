package com.liem.ms.productservice.command.service;

import com.liem.ms.productservice.core.dto.ProductDto;
import java.io.Serializable;

/**
 * The interface Product command service.
 *
 * @param <ID> the type parameter
 */
public interface ProductCommandService<ID extends Serializable> {

  /**
   * Create product dto.
   *
   * @param dto the dto
   * @return the product dto
   */
  ProductDto<ID> create(ProductDto<ID> dto);

  /**
   * Delete product dto.
   *
   * @param dto the dto
   * @return the product dto
   */
  ProductDto<ID> delete(ProductDto<ID> dto);

  /**
   * Update product dto.
   *
   * @param dto the dto
   * @return the product dto
   */
  ProductDto<ID> update(ProductDto<ID> dto);

}
