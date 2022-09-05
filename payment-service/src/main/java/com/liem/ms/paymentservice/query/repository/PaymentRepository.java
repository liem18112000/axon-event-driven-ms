package com.liem.ms.paymentservice.query.repository;

import com.liem.ms.paymentservice.query.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Payment repository.
 */
@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {

}

