package com.liem.ms.securityservice.config.cache;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * The type Cache configuration.
 */
@Configuration
public class CacheConfiguration {

  /**
   * The Host.
   */
  @Getter
  @Value("${spring.redis.host}")
  private String host;

  /**
   * The Port.
   */
  @Getter
  @Value("${spring.redis.port}")
  private String port;

  /**
   * The Password.
   */
  @Getter
  @Value("${spring.redis.password}")
  private String password;

  /**
   * Redis connection factory redis connection factory.
   *
   * @return the redis connection factory
   */
  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    configuration.setHostName(host);
    configuration.setPort(Integer.parseInt(port));
    configuration.setPassword(password);
    return new LettuceConnectionFactory(configuration);
  }

}
