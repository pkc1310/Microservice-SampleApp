package com.pkc.gamification.book.gamification.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameStats {

    private final Long userId;
    private final int score;
    private final List<Badge> badges;

    public GameStats() {
        this.userId = 0L;
        this.score = 0;
        this.badges = new ArrayList<>();
    }

    public GameStats(Long userId, int score, List<Badge> badges) {
        this.userId = userId;
        this.score = score;
        this.badges = badges;
    }

    public static GameStats emptyStats(final Long userId) {
        return new GameStats(userId, 0, Collections.emptyList());
    }

    public List<Badge> getBadges() {
        return Collections.unmodifiableList(badges);
    }

    public Long getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "GameStats{" +
                "userId=" + userId +
                ", score=" + score +
                ", badges=" + badges +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameStats gameStats = (GameStats) o;
        return score == gameStats.score && Objects.equals(userId, gameStats.userId) && Objects.equals(badges, gameStats.badges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, score, badges);
    }
}
