package com.liem.ms.commonservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.liem.ms.commonservice.dto.BaseDto;
import com.liem.ms.commonservice.entity.BaseEntity;
import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

/**
 * The interface Cache based query service.
 *
 * @param <ID>     the type parameter
 * @param <ENTITY> the type parameter
 * @param <DTO>    the type parameter
 */
public interface CacheBasedQueryService<
    ID extends Serializable,
    ENTITY extends BaseEntity<ID>,
    DTO extends BaseDto<ID>
    > extends QueryService<ID, ENTITY, DTO> {

  /**
   * Gets by cached.
   *
   * @param <KEY>                   the type parameter
   * @param <VALUE>                 the type parameter
   * @param key                     the key
   * @param handleWhenCacheNotFound the handle when cache not found
   * @param valueClass              the value class
   * @param duration                the duration
   * @return the by cached
   */
  <KEY extends Serializable, VALUE> VALUE getByCached(
      KEY key, Function<KEY, VALUE> handleWhenCacheNotFound,
      Class<VALUE> valueClass, Duration duration);

  /**
   * Gets all by cached.
   *
   * @param <KEY>                   the type parameter
   * @param <VALUE>                 the type parameter
   * @param key                     the key
   * @param handleWhenCacheNotFound the handle when cache not found
   * @param typeReference           the type reference
   * @param duration                the duration
   * @return the all by cached
   */
  <KEY extends Serializable, VALUE> List<VALUE> getAllByCached(
      KEY key, Function<KEY, List<VALUE>> handleWhenCacheNotFound,
      TypeReference<List<VALUE>> typeReference, Duration duration);
}
