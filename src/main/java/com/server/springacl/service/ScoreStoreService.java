package com.server.springacl.service;

import com.server.springacl.controller.dto.PlayerScoreDTO;
import com.server.springacl.controller.params.ScoreRequestBody;
import com.server.springacl.gateway.LeaderBoardTimeType;

import java.util.List;

public interface ScoreStoreService {
    boolean addScore(ScoreRequestBody body);

    List<PlayerScoreDTO> getTopPlayers(LeaderBoardTimeType timeType, Integer topN);
}
