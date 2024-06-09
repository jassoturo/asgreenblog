package com.server.springacl.service;

import com.server.springacl.gateway.LeaderBoardTimeType;
import com.server.springacl.gateway.ScoreStoreGateway;

public interface ScoreStoreChangeListener {

    boolean addScore(String playerUsername, Long score, Long timestamp);
}
