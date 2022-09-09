package com.liem.ms.commonservice.message.client;

import com.liem.ms.commonservice.dto.BaseDto;
import com.liem.ms.commonservice.message.BaseMessage;
import java.io.Serializable;

/**
 * The interface Message command client.
 *
 * @param <ID>  the type parameter
 * @param <DTO> the type parameter
 */
public interface MessageCommandClient
    <ID extends Serializable, DTO extends BaseDto<ID>> {

  /**
   * Create dto.
   *
   * @param dto the dto
   * @return the dto
   */
  BaseMessage<DTO> create(DTO dto);

  /**
   * Update dto.
   *
   * @param id  the id
   * @param dto the dto
   * @return the dto
   */
  BaseMessage<DTO> update(ID id, DTO dto);

  /**
   * Delete dto.
   *
   * @param id the id
   * @return the dto
   */
  BaseMessage<DTO> delete(ID id);

}
