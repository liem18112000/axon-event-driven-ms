package com.liem.ms.securityservice.service.cache;

import com.liem.ms.commonservice.service.impl.AbstractCacheService;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * The type Abstract redis cache service.
 *
 * @param <KEY>   the type parameter
 * @param <VALUE> the type parameter
 */
@RequiredArgsConstructor(onConstructor_={@Autowired})
public abstract class AbstractRedisCacheService
    <KEY extends Serializable, VALUE>
    extends AbstractCacheService<KEY, VALUE> {

  /**
   * The Template.
   */
  protected final RedisTemplate<KEY, VALUE> template;
}
