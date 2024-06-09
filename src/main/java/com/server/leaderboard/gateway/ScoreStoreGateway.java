package com.server.leaderboard.gateway;

import java.util.List;
import java.util.Map;

public interface ScoreStoreGateway {

    boolean addScore(LeaderBoardTimeType timeType, String playerUsername, Long score, Long timestamp);

    Double getScore(LeaderBoardTimeType timeType, String playerUsername, Long timestamp);

    List<String> getCurrentToplayers(LeaderBoardTimeType timeType, Integer topN);
    Map<String, Double> getCurrentToplayersWithScore(LeaderBoardTimeType timeType, Integer topN);
}
