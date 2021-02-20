package com.pkc.gamification.book.gamification.client;

import com.pkc.gamification.book.gamification.client.dto.MultiplicationResultAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class MultiplicationResultAttemptClientImpl implements MultiplicationResultAttemptClient{

    private final RestTemplate restTemplate;
    private final String multiplicationHost;

    @Autowired
    public MultiplicationResultAttemptClientImpl
            (final RestTemplate restTemplate,
             @Value("${multiplicationHost}") final String
                     multiplicationHost) {
        this.restTemplate = restTemplate;
        this.multiplicationHost = multiplicationHost;
    }

    @Override
    public MultiplicationResultAttempt retrieveMultiplicationResultAttemptbyId(final Long multiplicationResultAttemptId) {
        return restTemplate.getForObject(
                multiplicationHost + "/results/" +
                        multiplicationResultAttemptId,
                MultiplicationResultAttempt.class);
    }

}
