package com.pkc.gamification.book.gamification.event;

import com.pkc.gamification.book.gamification.service.GameService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {
    private GameService gameService;

    @Autowired
    EventHandler(final GameService gameService) {
        this.gameService = gameService;
    }

    //TODO add Logger
    @RabbitListener(queues = "${multiplication.queue}")
    void handleMultiplicationSolved(final MultiplicationSolvedEvent event) {
        System.out.println("Multiplication Solved Event received: {} "+event.getMultiplicationResultAttemptId());
        try {
            gameService.newAttemptForUser(event.getUserId(),
                    event.getMultiplicationResultAttemptId(),
                    event.isCorrect());
        } catch (final Exception e) {

            System.out.println("Error when trying to processMultiplicationSolvedEvent "+ e);
                    // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
