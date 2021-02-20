package com.pkc.socialmultiplication.book.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkc.socialmultiplication.book.multiplication.domain.Multiplication;
import com.pkc.socialmultiplication.book.multiplication.domain.MultiplicationResultAttempt;
import com.pkc.socialmultiplication.book.multiplication.domain.User;
import com.pkc.socialmultiplication.book.multiplication.service.MultiplicationService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@WebMvcTest(MultiplicationResultAttemptController.class)
public class MultiplicationResultAttemptControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationResultAttempt> jsonResult;
    private JacksonTester<MultiplicationResultAttempt> jsonResponse;
    private JacksonTester<List<MultiplicationResultAttempt>>
            jsonResultAttemptList;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }
    @Test
    public void postResultReturnCorrect() throws Exception {
        genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnNotCorrect() throws Exception {
        genericParameterizedTest(false);
    }

    void genericParameterizedTest(final boolean correct) throws Exception {
        // given (remember we're not testing here the service itself)
        given(multiplicationService
                .checkAttempt(any(MultiplicationResultAttempt. class)))
                .willReturn(correct);
        User user = new User("john");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new
                MultiplicationResultAttempt(
                user, multiplication, 3500, correct);
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/results").contentType(MediaType.
                        APPLICATION_JSON)
                        .content(jsonResult.write(attempt).
                                getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.
                OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonResponse.write(new
                        MultiplicationResultAttempt(attempt.
                        getUser(),
                        attempt.getMultiplication(),
                        attempt.getResultAttempt(),
                        correct)).getJson());
    }

    @Test
    public void getUserStats() throws Exception {
        // given
        User user = new User("john_doe");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new
                MultiplicationResultAttempt(user, multiplication, 3500, true);
        List<MultiplicationResultAttempt> recentAttempts = Lists.newArrayList(attempt, attempt);
        given(multiplicationService
                .getStatsForUser("john_doe"))
                .willReturn(recentAttempts);
        // when
        MockHttpServletResponse response = mvc.perform(
                get("/results").param("alias", "john_doe"))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonResultAttemptList.write(
                        recentAttempts
                ).getJson());
    }
}
