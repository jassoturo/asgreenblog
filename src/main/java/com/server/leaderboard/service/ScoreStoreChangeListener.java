package com.server.leaderboard.service;

import com.server.leaderboard.gateway.LeaderBoardTimeType;
import com.server.leaderboard.gateway.ScoreStoreGateway;

public interface ScoreStoreChangeListener {

    boolean addScore(String playerUsername, Long score, Long timestamp);
}
