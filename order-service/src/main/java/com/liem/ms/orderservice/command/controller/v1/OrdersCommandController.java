package com.liem.ms.orderservice.command.controller.v1;

import com.liem.ms.orderservice.command.service.OrderCommandService;
import com.liem.ms.orderservice.core.dto.OrderDto;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Orders command controller.
 */
@RestController
@RequestMapping("v1/orders")
@AllArgsConstructor
public class OrdersCommandController {

  /**
   * The Command service.
   */
  private final OrderCommandService<String> commandService;

  /**
   * Create order string.
   *
   * @param order the order
   * @return the string
   */
  @PostMapping
  public ResponseEntity<OrderDto<String>> createOrder(
      @Valid @RequestBody OrderDto<String> order) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(this.commandService.create(order));
  }

}
