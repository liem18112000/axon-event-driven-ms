package com.liem.ms.orderservice.command.controller.v1;

import static com.liem.ms.orderservice.core.model.OrderStatus.APPROVED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

import com.liem.ms.orderservice.command.service.OrderCommandService;
import com.liem.ms.orderservice.core.dto.OrderDto;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
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
  public ResponseEntity<?> createOrder(
      @Valid @RequestBody OrderDto<String> order) {
    final var createdOrder = this.commandService.create(order);
    final var status = createdOrder.getOrderStatus();
    return ResponseEntity
        .status(status.equals(APPROVED) ? CREATED : BAD_REQUEST)
        .body(createdOrder);
  }

}
