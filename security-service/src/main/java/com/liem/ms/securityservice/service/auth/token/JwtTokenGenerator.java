package com.liem.ms.securityservice.service.auth.token;

import com.liem.ms.commonservice.auth.token.TokenGenerator;
import com.liem.ms.commonservice.dto.BaseDto;
import com.liem.ms.securityservice.config.security.JwtConfiguration;
import com.liem.ms.securityservice.dto.user.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Jwt token generator.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class JwtTokenGenerator
    implements TokenGenerator<UserDto<Long>, String> {

  /**
   * The Configuration.
   */
  private final JwtConfiguration configuration;

  /**
   * Generate token string.
   *
   * @param userDto the user dto
   * @return the string
   */
  @Override
  public String generateToken(final UserDto<Long> userDto) {
    final var principal = userDto.getUsername();
    final var jwtSecret = this.configuration.getTokenSecret();
    final var authorities = userDto.getRoles().stream()
        .map(BaseDto::getName).collect(Collectors.toSet());
    final var issuedAt = new Date();
    return Jwts.builder()
        .setHeaderParam("typ", "JWT")
        .setSubject(principal)
        .claim("authorities", authorities)
        .setIssuedAt(issuedAt)
        .setExpiration(this.calculateExpiration(issuedAt))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  /**
   * Calculate expiration date.
   *
   * @param from the from
   * @return the date
   */
  protected Date calculateExpiration(final Date from) {
    final var jwtExpiration = this.configuration.getTokenExpiration();
    return new Date(from.getTime() + jwtExpiration.toMillis());
  }
}
