package com.liem.ms.securityservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The type Security service application.
 */
@EnableRabbit
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={
    "com.liem.ms.commonservice",
    "com.liem"
})
public class SecurityServiceApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(SecurityServiceApplication.class, args);
  }

}
