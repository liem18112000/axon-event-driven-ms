package com.liem.ms.gatewayservice.entity.route;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * The interface Route entity repository.
 */
@Repository
public interface RouteEntityRepository
    extends ReactiveCrudRepository<RouteEntity, Long> {

  /**
   * Find by service name and active is true mono.
   *
   * @param serviceName the service name
   * @return the mono
   */
  Mono<RouteEntity> findByServiceNameAndActiveIsTrue(String serviceName);
}
