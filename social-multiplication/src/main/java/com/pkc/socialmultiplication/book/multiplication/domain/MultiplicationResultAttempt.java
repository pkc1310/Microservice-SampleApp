package com.pkc.socialmultiplication.book.multiplication.domain;

import javax.persistence.*;

@Entity
public final class MultiplicationResultAttempt {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private final User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MULTIPLICATION_ID")
    private final Multiplication multiplication;

    private final int resultAttempt;

    private final boolean correct;

    public MultiplicationResultAttempt(User user, Multiplication multiplication,
                                       int resultAttempt, boolean correct){
        this.multiplication=multiplication;
        this.user=user;
        this.resultAttempt=resultAttempt;
        this.correct=correct;
    }

    public MultiplicationResultAttempt() {
        user = null;
        multiplication = null;
        resultAttempt = -1;
        correct = false;
    }

    public User getUser() {
        return user;
    }

    public Multiplication getMultiplication() {
        return multiplication;
    }

    public int getResultAttempt() {
        return resultAttempt;
    }

    public boolean isCorrect() {
        return correct;
    }

    public Long getId() {
        return id;
    }
}
