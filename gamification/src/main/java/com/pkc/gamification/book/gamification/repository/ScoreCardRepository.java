package com.pkc.gamification.book.gamification.repository;

import com.pkc.gamification.book.gamification.domain.LeaderBoardRow;
import com.pkc.gamification.book.gamification.domain.ScoreCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long> {

    @Query("SELECT SUM(s.score) FROM com.pkc.gamification.book.gamification.domain.ScoreCard s " +
            "WHERE s.userId = :userId  GROUP BY s.userId")
    int getTotalScoreForUser(@Param("userId") final Long  userId);

    @Query("SELECT NEW com.pkc.gamification.book.gamification.domain.LeaderBoardRow(s.userId, SUM(s.score)) " +
            "FROM com.pkc.gamification.book.gamification.domain.ScoreCard s " +
            "GROUP BY s.userId ORDER BY SUM(s.score) DESC")
    List<LeaderBoardRow> findFirst10();

    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(final Long userId);
}
