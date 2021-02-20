package com.pkc.socialmultiplication.book.multiplication.service;

import com.pkc.socialmultiplication.book.multiplication.domain.Multiplication;
import com.pkc.socialmultiplication.book.multiplication.domain.MultiplicationResultAttempt;
import com.pkc.socialmultiplication.book.multiplication.domain.User;
import com.pkc.socialmultiplication.book.multiplication.event.EventDispatcher;
import com.pkc.socialmultiplication.book.multiplication.repository.MultiplicationResultAttemptRepository;
import com.pkc.socialmultiplication.book.multiplication.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

@SpringBootTest
public class MultiplicationServiceTest {

    //@Autowired
    private MultiplicationService multiplicationService;

    @MockBean
    private RandomGeneratorService randomGeneratorService;

    @MockBean
    private MultiplicationResultAttemptRepository attemptRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private EventDispatcher eventDispatcher;

    @BeforeEach
    public void setUp() {
        // With this call to initMocks we tell Mockito to process the annotations
        MockitoAnnotations.initMocks(this);
        multiplicationService = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository, userRepository,eventDispatcher);
    }



    @Test
    public void createRandomMultiplicationTest() {
        // given (our mocked Random Generator service will return first 50, then 30)

        given(randomGeneratorService.generateRandomFactor()).
                willReturn(50, 30);
        // when
        Multiplication multiplication = multiplicationService.
                createRandomMultiplication();
        // then
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getResult()).isEqualTo(1500);
    }

    @Test
    public void checkCorrectAttemptTest() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationResultAttempt attempt =
                new MultiplicationResultAttempt(user, multiplication, 3000,false);
        MultiplicationResultAttempt verifiedAttempt =
                new MultiplicationResultAttempt(user, multiplication, 3000, true);
        given(userRepository.findByAlias("john_doe")).
                willReturn(Optional.empty());
        // when
        boolean attemptResult = multiplicationService.checkAttempt(attempt);
        // assert
        assertThat(attemptResult).isTrue();
       // verify(attemptRepository).save(verifiedAttempt);
    }
    @Test
    public void checkWrongAttemptTest() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010,false);
        given(userRepository.findByAlias("john_doe")).
                willReturn(Optional.empty());
        // when
        boolean attemptResult = multiplicationService.checkAttempt(attempt);
        // assert
        assertThat(attemptResult).isFalse();
       // verify(attemptRepository).save(attempt);
    }

    @Test
    public void retrieveStatsTest() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("john_doe");
        MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(
                user, multiplication, 3010, false);

        MultiplicationResultAttempt attempt2 = new  MultiplicationResultAttempt(
                user, multiplication, 3051, false);

        List<MultiplicationResultAttempt> latestAttempts = Lists.newArrayList(attempt1, attempt2);

        given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());

        given(attemptRepository.findTop5ByUserAliasOrderByIdDesc("john_doe"))
                .willReturn(latestAttempts);
        // when
        List<MultiplicationResultAttempt> latestAttemptsResult = multiplicationService.getStatsForUser("john_doe");
        // then
        assertThat(latestAttemptsResult).isEqualTo(latestAttempts);
    }

}



