package com.pkc.gamification.book.gamification.service;

import com.pkc.gamification.book.gamification.domain.LeaderBoardRow;

import java.util.List;

public interface LeaderBoardService {
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
