package com.liem.ms.commonservice.service.impl;

import com.liem.ms.commonservice.service.CacheService;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.Duration;
import java.util.function.Supplier;

/**
 * The type Abstract cache service.
 *
 * @param <KEY>   the type parameter
 * @param <VALUE> the type parameter
 */
public abstract class AbstractCacheService
    <KEY extends Serializable, VALUE>
    implements CacheService<KEY, VALUE> {

  /**
   * Get value.
   *
   * @param key     the key
   * @param getReal the get real
   * @return the value
   */
  @Override
  public VALUE get(
      final @NotNull KEY key,
      final @NotNull Supplier<VALUE> getReal) {
    final var cachedValue = this.get(key);
    if (cachedValue == null) {
      final var value = getReal.get();
      this.cache(key, value);
      return getReal.get();
    }
    return cachedValue;
  }

  /**
   * Get value.
   *
   * @param key      the key
   * @param getReal  the get real
   * @param duration the duration
   * @return the value
   */
  @Override
  public VALUE get(
      final @NotNull KEY key,
      final @NotNull Supplier<VALUE> getReal,
      final @NotNull Duration duration) {
    final var cachedValue = this.get(key);
    if (cachedValue == null) {
      final var value = getReal.get();
      this.cache(key, value, duration);
      return getReal.get();
    }
    return cachedValue;
  }
}
