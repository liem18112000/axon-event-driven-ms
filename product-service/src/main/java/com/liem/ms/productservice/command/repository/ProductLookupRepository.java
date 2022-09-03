package com.liem.ms.productservice.command.repository;

import com.liem.ms.productservice.command.entity.ProductLookupEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Product lookup repository.
 */
@Repository
public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {

  /**
   * Find by product id optional.
   *
   * @param productId the product id
   * @return the optional
   */
  Optional<ProductLookupEntity> findByProductId(String productId);

  /**
   * Exists by product name boolean.
   *
   * @param productName the product name
   * @return the boolean
   */
  boolean existsByProductName(String productName);

  /**
   * Find by product name optional.
   *
   * @param productName the product name
   * @return the optional
   */
  Optional<ProductLookupEntity> findByProductName(String productName);

}