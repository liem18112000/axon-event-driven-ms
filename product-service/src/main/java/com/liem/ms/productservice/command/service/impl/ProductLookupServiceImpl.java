package com.liem.ms.productservice.command.service.impl;

import com.liem.ms.productservice.command.repository.ProductLookupRepository;
import com.liem.ms.productservice.command.service.ProductLookupService;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Product lookup service.
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProductLookupServiceImpl implements ProductLookupService {

  /**
   * The Repository.
   */
  private final ProductLookupRepository repository;

  /**
   * Is product exist by name boolean.
   *
   * @param productName the product name
   * @return the boolean
   */
  @Override
  public boolean isProductExistByName(final String productName) {
    return this.repository.existsByProductName(productName);
  }

  /**
   * Is updated product exist by name boolean.
   *
   * @param productId   the product id
   * @param productName the product name
   * @return the boolean
   */
  @Override
  public boolean isUpdatedProductExistByName(
      final String productId, final String productName) {
    final var lookupEntity = this.repository.findByProductName(productName);
    return lookupEntity.isPresent()
        && !Objects.equals(lookupEntity.get().getProductId(), productId);
  }
}
