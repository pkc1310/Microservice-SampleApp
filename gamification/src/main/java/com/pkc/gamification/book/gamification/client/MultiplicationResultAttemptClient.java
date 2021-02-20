package com.pkc.gamification.book.gamification.client;

import com.pkc.gamification.book.gamification.client.dto.MultiplicationResultAttempt;

public interface MultiplicationResultAttemptClient {

    MultiplicationResultAttempt retrieveMultiplicationResultAttemptbyId(final Long multiplicationId);

}
