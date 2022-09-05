package com.liem.ms.productservice.query.repository;

import com.liem.ms.productservice.query.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * The interface Product repository.
 */
@Repository
public interface ProductRepository
    extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {

}
