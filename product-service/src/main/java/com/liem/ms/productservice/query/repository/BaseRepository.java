package com.liem.ms.productservice.query.repository;

import com.liem.ms.productservice.query.entity.BaseEntity;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The interface Base repository.
 *
 * @param <T>  the type parameter
 * @param <ID> the type parameter
 */
public interface BaseRepository
    <T extends BaseEntity<ID>, ID extends Serializable>
    extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
