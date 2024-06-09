package com.server.leaderboard.gateway;

import com.server.leaderboard.gateway.helper.ScoreStoreRedisKeyHelper;
import com.server.leaderboard.shared.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
@Slf4j
public class ScoreStoreGatewayRedisImpl implements ScoreStoreGateway {

    private final RedisTemplate<String, String> redisTemplate;
    @Override
    public boolean addScore(LeaderBoardTimeType timeType, String playerUsername, Long score, Long timestamp) {
        String key = ScoreStoreRedisKeyHelper.getLeaderboardKeyBaseOnTimeType(timestamp, timeType);

        Double result = redisTemplate.opsForZSet().incrementScore(key, playerUsername, score);

        return result != null;
    }

    @Override
    public Double getScore(LeaderBoardTimeType timeType, String playerUsername, Long timestamp) {
        String key = ScoreStoreRedisKeyHelper.getLeaderboardKeyBaseOnTimeType(timestamp, timeType);
        Double result = redisTemplate.opsForZSet().score(key, playerUsername);

        return result != null ? result : 0.0;
    }

    @Override
    public List<String> getCurrentToplayers(LeaderBoardTimeType timeType, Integer topN) {
        String key = ScoreStoreRedisKeyHelper.getLeaderboardKeyBaseOnTimeType(TimeUtil.getCurrentTimestamp(), timeType);
        return List.copyOf(redisTemplate.opsForZSet().reverseRange(key, 0, topN - 1));
    }

    @Override
    public Map<String, Double> getCurrentToplayersWithScore(LeaderBoardTimeType timeType, Integer topN) {
        String key = ScoreStoreRedisKeyHelper.getLeaderboardKeyBaseOnTimeType(TimeUtil.getCurrentTimestamp(), timeType);
        Set<ZSetOperations.TypedTuple<String>> resultSet = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, topN - 1);

        Map<String, Double> topPlayersWithScores = new HashMap<>();
        if (resultSet != null) {
            for (ZSetOperations.TypedTuple<String> entry : resultSet) {
                if (entry.getValue() != null && entry.getScore() != null) {
                    topPlayersWithScores.put(entry.getValue(), entry.getScore());
                }
            }
        }
        return topPlayersWithScores;
    }
}
