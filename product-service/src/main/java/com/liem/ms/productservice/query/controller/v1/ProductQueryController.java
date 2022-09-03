package com.liem.ms.productservice.query.controller.v1;

import com.liem.ms.productservice.query.service.ProductQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Product query controller.
 */
@RestController
@RequestMapping("v1/products")
@AllArgsConstructor
public class ProductQueryController {

  /**
   * The Query service.
   */
  private final ProductQueryService queryService;

  /**
   * Gets products.
   *
   * @param pageable the pageable
   * @return the products
   */
  @GetMapping
  public ResponseEntity<?> getProducts() {
    return ResponseEntity.ok(this.queryService.findProducts());
  }

}
