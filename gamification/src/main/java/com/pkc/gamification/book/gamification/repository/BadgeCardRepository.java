package com.pkc.gamification.book.gamification.repository;

import com.pkc.gamification.book.gamification.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeCardRepository extends CrudRepository<BadgeCard, Long> {
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);
}
