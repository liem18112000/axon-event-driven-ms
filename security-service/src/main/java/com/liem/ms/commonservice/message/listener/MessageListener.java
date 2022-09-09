package com.liem.ms.commonservice.message.listener;

import com.liem.ms.commonservice.dto.BaseDto;
import com.liem.ms.commonservice.message.BaseMessage;
import java.io.Serializable;

/**
 * The interface Message listener.
 *
 * @param <ID>  the type parameter
 * @param <DTO> the type parameter
 */
public interface MessageListener
    <ID extends Serializable, DTO extends BaseDto<ID>> {

  /**
   * Handle request rest message.
   *
   * @param restMessage the rest message
   * @return the rest message
   */
  BaseMessage<DTO> handleRequest(final BaseMessage<DTO> restMessage);
}
