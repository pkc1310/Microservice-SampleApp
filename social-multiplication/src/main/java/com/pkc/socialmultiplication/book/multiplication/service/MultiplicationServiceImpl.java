package com.pkc.socialmultiplication.book.multiplication.service;

import com.pkc.socialmultiplication.book.multiplication.domain.Multiplication;
import com.pkc.socialmultiplication.book.multiplication.domain.MultiplicationResultAttempt;
import com.pkc.socialmultiplication.book.multiplication.domain.User;
import com.pkc.socialmultiplication.book.multiplication.event.EventDispatcher;
import com.pkc.socialmultiplication.book.multiplication.event.MultiplicationSolvedEvent;
import com.pkc.socialmultiplication.book.multiplication.repository.MultiplicationResultAttemptRepository;
import com.pkc.socialmultiplication.book.multiplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MultiplicationServiceImpl implements MultiplicationService{

    private final RandomGeneratorService randomGeneratorService;

    private final MultiplicationResultAttemptRepository attemptRepository;

    private final UserRepository userRepository;

    private final EventDispatcher eventDispatcher;

    @Autowired
    public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService,
                                     MultiplicationResultAttemptRepository attemptRepository,
                                     UserRepository userRepository,
                                     final EventDispatcher eventDispatcher) {
        this.randomGeneratorService = randomGeneratorService;
        this.attemptRepository=attemptRepository;
        this.userRepository=userRepository;
        this.eventDispatcher=eventDispatcher;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(MultiplicationResultAttempt attempt) {
        // Check if the user already exists for that alias
        Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());

        // Avoids 'hack' attempts
        Assert.isTrue(!attempt.isCorrect(), "You can't send an attempt marked as correct!!");

        // Checks if it's correct
        boolean isCorrect = attempt.getResultAttempt() ==
                attempt.getMultiplication().getFactorA() * attempt.getMultiplication().getFactorB();

        // Creates a copy, now setting the 'correct' field accordingly
        MultiplicationResultAttempt checkedAttempt =
                new MultiplicationResultAttempt(user.orElse(attempt.getUser()),
                        attempt.getMultiplication(),attempt.getResultAttempt(),isCorrect);

        // Stores the attempt
        attemptRepository.save(checkedAttempt);

        // Communicates the result via Event
        eventDispatcher.send(new MultiplicationSolvedEvent(checkedAttempt.getId(),
                        checkedAttempt.getUser().getId(),
                        checkedAttempt.isCorrect())
        );

        // Returns the result
        return isCorrect;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }

    @Override
    public MultiplicationResultAttempt getMultiplicationAttampt(Long attamptId) {
        return attemptRepository.findById(attamptId).orElse(new MultiplicationResultAttempt());
    }
}
