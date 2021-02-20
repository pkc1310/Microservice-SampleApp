package com.pkc.gamification.book.gamification.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class BadgeCard {

    @Id
    @GeneratedValue
    @Column(name = "BADGE_ID")
    private final Long badgeId;
    private final Long userId;
    private final long badgeTimestamp;
    private final Badge badge;

    public BadgeCard(Long badgeId, Long userId, long badgeTimestamp, Badge badge){
        this.badgeId=badgeId;
        this.userId=userId;
        this.badgeTimestamp=badgeTimestamp;
        this.badge=badge;
    }

    public BadgeCard() {
        this(null, null, 0, null);
    }
    public BadgeCard(final Long userId, final Badge badge) {
        this(null, userId, System.currentTimeMillis(), badge);
    }

    public Long getBadgeId() {
        return badgeId;
    }

    public Long getUserId() {
        return userId;
    }

    public long getBadgeTimestamp() {
        return badgeTimestamp;
    }

    public Badge getBadge() {
        return badge;
    }

    @Override
    public String toString() {
        return "BadgeCard{" +
                "badgeId=" + badgeId +
                ", userId=" + userId +
                ", badgeTimestamp=" + badgeTimestamp +
                ", badge=" + badge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BadgeCard badgeCard = (BadgeCard) o;
        return badgeTimestamp == badgeCard.badgeTimestamp && Objects.equals(badgeId, badgeCard.badgeId) && Objects.equals(userId, badgeCard.userId) && badge == badgeCard.badge;
    }

    @Override
    public int hashCode() {
        return Objects.hash(badgeId, userId, badgeTimestamp, badge);
    }
}
