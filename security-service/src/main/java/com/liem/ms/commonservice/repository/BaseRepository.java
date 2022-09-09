package com.liem.ms.commonservice.repository;

import com.liem.ms.commonservice.entity.BaseEntity;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The interface Base repository.
 *
 * @param <ENTITY> the type parameter
 * @param <ID>     the type parameter
 */
public interface BaseRepository<ENTITY extends BaseEntity<ID>, ID extends Serializable>
    extends JpaRepository<ENTITY, ID>, JpaSpecificationExecutor<ENTITY> {

}
