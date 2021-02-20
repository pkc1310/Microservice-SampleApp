package com.pkc.gamification.book.gamification.service;

import com.pkc.gamification.book.gamification.domain.GameStats;

public interface GameService {

    GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct);

    GameStats retrieveStatsForUser(Long userId);

}
