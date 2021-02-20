package com.pkc.gamification.book.gamification.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pkc.gamification.book.gamification.client.MultiplicationResultAttemptDeserializer;

import java.util.Objects;

@JsonDeserialize(using = MultiplicationResultAttemptDeserializer.class)
public final class MultiplicationResultAttempt {

    private final String userAlias;
    private final int multiplicationFactorA;
    private final int multiplicationFactorB;
    private final int resultAttempt;
    private final boolean correct;

    MultiplicationResultAttempt() {
        userAlias = null;
        multiplicationFactorA = -1;
        multiplicationFactorB = -1;
        resultAttempt = -1;
        correct = false;
    }

    public MultiplicationResultAttempt(String userAlias, int multiplicationFactorA, int multiplicationFactorB,
                                       int resultAttempt, boolean correct) {
        this.userAlias = userAlias;
        this.multiplicationFactorA = multiplicationFactorA;
        this.multiplicationFactorB = multiplicationFactorB;
        this.resultAttempt = resultAttempt;
        this.correct = correct;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public int getMultiplicationFactorA() {
        return multiplicationFactorA;
    }

    public int getMultiplicationFactorB() {
        return multiplicationFactorB;
    }

    public int getResultAttempt() {
        return resultAttempt;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return "MultiplicationResultAttempt{" +
                "userAlias='" + userAlias + '\'' +
                ", multiplicationFactorA=" + multiplicationFactorA +
                ", multiplicationFactorB=" + multiplicationFactorB +
                ", resultAttempt=" + resultAttempt +
                ", correct=" + correct +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiplicationResultAttempt that = (MultiplicationResultAttempt) o;
        return multiplicationFactorA == that.multiplicationFactorA && multiplicationFactorB == that.multiplicationFactorB && resultAttempt == that.resultAttempt && correct == that.correct && Objects.equals(userAlias, that.userAlias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAlias, multiplicationFactorA, multiplicationFactorB, resultAttempt, correct);
    }
}
