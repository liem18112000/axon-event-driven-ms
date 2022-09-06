package com.liem.ms.orderservice.query.controller.v1;

import com.liem.ms.orderservice.query.service.OrderQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Orders command controller.
 */
@RestController
@RequestMapping("v1/orders")
@AllArgsConstructor
public class OrdersQueryController {

  /**
   * The Query service.
   */
  private final OrderQueryService queryService;

  /**
   * Create order string.
   *
   * @param id the order id
   * @return the string
   */
  @GetMapping("{id}")
  public ResponseEntity<?> getOrderById(
      final @PathVariable String id) {
    return ResponseEntity.ok(this.queryService.queryOrderById(id));
  }
}
