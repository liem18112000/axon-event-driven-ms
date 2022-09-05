package com.liem.ms.userservice.query.controller.v1;
 
import com.liem.ms.coreservice.models.User;
import com.liem.ms.coreservice.queries.FetchUserPaymentDetailsQuery;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Users query controller.
 */
@RestController
@RequestMapping("v1/users")
@AllArgsConstructor
public class UsersQueryController {

    /**
     * The Query gateway.
     */
    private final QueryGateway queryGateway;

    /**
     * Gets user payment details.
     *
     * @param userId the user id
     * @return the user payment details
     */
    @GetMapping("/{userId}/payment-details")
    public User getUserPaymentDetails(@PathVariable String userId) {
        final var query = FetchUserPaymentDetailsQuery.builder()
            .userId(userId)
            .build();
        return queryGateway.query(query, ResponseTypes.instanceOf(User.class)).join();
    }

}
