package com.liem.ms.orderservice.command.repository;

import com.liem.ms.orderservice.command.entity.OrderLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Order lookup repository.
 */
@Repository
public interface OrderLookupRepository extends JpaRepository<OrderLookupEntity, String> {

}
