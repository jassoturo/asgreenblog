package com.server.springacl.service;

import com.server.springacl.gateway.LeaderBoardTimeType;
import com.server.springacl.gateway.ScoreStoreGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ScoreStoreChangeListenerImpl implements ScoreStoreChangeListener{

    private final ScoreStoreGateway scoreStoreGateway;
    @Override
    public boolean addScore(String playerUsername, Long score, Long timestamp) {
        LeaderBoardTimeType[] timeTypes = LeaderBoardTimeType.values();
        boolean success = false;
        for (LeaderBoardTimeType timeType : timeTypes) {
            if (!scoreStoreGateway.addScore(timeType, playerUsername, score, timestamp)) {
                log.error("Failed to add score for player: {} with score: {} and timestamp: {} for timeType: {}", playerUsername, score, timestamp, timeType);
                // add logic to retry, handle failure case
                success = false;
            } else {
                success = true;
            }
        }

        return success;
    }
}
