package com.liem.ms.userservice.query.handler;

import com.liem.ms.coreservice.models.PaymentDetails;
import com.liem.ms.coreservice.models.User;
import com.liem.ms.coreservice.queries.FetchUserPaymentDetailsQuery;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

/**
 * The type User events handler.
 */
@Slf4j
@Component
@AllArgsConstructor
public class UserEventsHandler {

    /**
     * Find user payment details user.
     *
     * @param query the query
     * @return the user
     */
    @QueryHandler
    public User findUserPaymentDetails(final @NotNull FetchUserPaymentDetailsQuery query) {
        log.info("Find user payment detail query handle; {}", query);
        final var paymentDetails = PaymentDetails.builder()
                .cardNumber("123Card")
                .cvv("123")
                .name("Liem Doan")
                .validUntilMonth(12)
                .validUntilYear(2030)
                .build();
        return User.builder()
                .firstName("Liem")
                .lastName("Doan")
                .userId(query.getUserId())
                .paymentDetails(paymentDetails)
                .build();
    }
    
}
