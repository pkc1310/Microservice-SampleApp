package com.pkc.gamification.book.gamification.service;

import com.pkc.gamification.book.gamification.domain.LeaderBoardRow;
import com.pkc.gamification.book.gamification.repository.ScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService{

    private ScoreCardRepository scoreCardRepository;

    @Autowired
    public LeaderBoardServiceImpl (ScoreCardRepository scoreCardRepository){
        this.scoreCardRepository=scoreCardRepository;

    }

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        return scoreCardRepository.findFirst10();
    }
}
