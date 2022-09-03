package com.liem.ms.productservice.command.controller.v1;

import com.liem.ms.productservice.command.service.ProductCommandService;
import com.liem.ms.productservice.core.dto.ProductDto;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("v1/products")
@AllArgsConstructor
public class ProductCommandController {

  /**
   * The Command service.
   */
  private final ProductCommandService<String> commandService;

  /**
   * Create product response entity.
   *
   * @param dto the dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<?> createProduct(
      final @RequestBody @Valid ProductDto<String> dto) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(this.commandService.create(dto));
  }

  /**
   * Update product response entity.
   *
   * @param dto the dto
   * @return the response entity
   */
  @PutMapping
  public ResponseEntity<?> updateProduct(
      final @RequestBody @Valid ProductDto<String> dto) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.commandService.update(dto));
  }

  /**
   * Delete product response entity.
   *
   * @param dto the dto
   * @return the response entity
   */
  @DeleteMapping
  public ResponseEntity<?> deleteProduct(
      final @RequestBody ProductDto<String> dto) {
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .body(this.commandService.delete(dto));
  }



}
