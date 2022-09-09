package com.liem.ms.securityservice.entity.listener;

import com.liem.ms.commonservice.service.bean.BeanService;
import com.liem.ms.securityservice.entity.UserEntity;
import com.liem.ms.securityservice.service.query.UserQueryService;
import java.util.Objects;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * The type User entity listener.
 */
@Slf4j
public class UserEntityListener {

  /**
   * The User query service.
   */
  private UserQueryService userQueryService;

  /**
   * Instantiates a new User query service.
   */
  private void initBeans() {
    if (this.userQueryService == null) {
      this.userQueryService = BeanService.getBean(UserQueryService.class);
    }
  }

  /**
   * Handle before save user.
   *
   * @param entity the entity
   */
  @PrePersist
  public void handleBeforeSaveUser(final UserEntity entity) {
    this.initBeans();
    if (this.userQueryService.getActiveUserByUsernameWithoutException(
        entity.getUsername()) != null) {
      throw new ValidationException("Username is duplicated");
    }
  }

  /**
   * Handle before update user.
   *
   * @param entity the entity
   */
  @PreUpdate
  public void handleBeforeUpdateUser(final UserEntity entity) {
    this.initBeans();
    final var user = this.userQueryService
        .getActiveUserByUsernameWithoutException(entity.getUsername());
    if (user != null) {
      if (!Objects.equals(user.getId(), entity.getId())) {
        throw new ValidationException("Username is duplicated");
      }
    }
  }
}
