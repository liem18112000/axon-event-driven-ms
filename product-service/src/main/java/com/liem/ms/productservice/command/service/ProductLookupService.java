package com.liem.ms.productservice.command.service;

/**
 * The interface Product lookup service.
 */
public interface ProductLookupService {

  /**
   * Is product exist by name boolean.
   *
   * @param productName the product name
   * @return the boolean
   */
  boolean isProductExistByName(String productName);

  /**
   * Is updated product exist by name boolean.
   *
   * @param productId   the product id
   * @param productName the product name
   * @return the boolean
   */
  boolean isUpdatedProductExistByName(String productId, String productName);

  /**
   * Is reserve sufficient boolean.
   *
   * @param productId        the product id
   * @param reservedQuantity the reserved quantity
   * @return the boolean
   */
  boolean isReserveSufficient(String productId, Integer reservedQuantity);
}
