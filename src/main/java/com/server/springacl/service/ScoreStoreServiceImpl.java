package com.server.springacl.service;

import com.server.springacl.controller.dto.PlayerScoreDTO;
import com.server.springacl.controller.params.ScoreRequestBody;
import com.server.springacl.gateway.LeaderBoardTimeType;
import com.server.springacl.gateway.ScoreStoreGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class ScoreStoreServiceImpl implements ScoreStoreService {

    private final ScoreStoreChangeListener scoreStoreChangeListener;
    private final ScoreStoreGateway scoreStoreGateway;
    @Override
    public boolean addScore(ScoreRequestBody body) {
        return scoreStoreChangeListener.addScore(body.getPlayerUsername(), body.getScore().longValue(), body.getTimestamp());
    }

    @Override
    public List<PlayerScoreDTO> getTopPlayers(LeaderBoardTimeType timeType, Integer topN) {
        Map<String, Double> currentToplayersWithScore =
                scoreStoreGateway.getCurrentToplayersWithScore(timeType, topN);
        return currentToplayersWithScore.entrySet().stream()
                .map(entry -> new PlayerScoreDTO(entry.getKey(), entry.getValue()))
                .sorted((p1, p2) -> Double.compare(p2.getScore(), p1.getScore()))
                .toList();
    }
}
