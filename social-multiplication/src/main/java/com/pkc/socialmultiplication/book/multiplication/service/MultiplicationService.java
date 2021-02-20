package com.pkc.socialmultiplication.book.multiplication.service;

import com.pkc.socialmultiplication.book.multiplication.domain.Multiplication;
import com.pkc.socialmultiplication.book.multiplication.domain.MultiplicationResultAttempt;

import java.util.List;
import java.util.Optional;

public interface MultiplicationService {

    Multiplication createRandomMultiplication();

    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);

    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias);

    public MultiplicationResultAttempt getMultiplicationAttampt(Long attamptId);

}
