package com.tomkasp.training.application.command;

import org.springframework.data.geo.Distance;

import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
public class UpdateTrainingHistoryCommand {

    private final Long trainingHistoryId;

    private final Distance distance;

    private final Duration personalRecord;

    private final Duration lastTime;

    public UpdateTrainingHistoryCommand(
        Long trainingHistoryId,
        Distance distance,
        Duration personalRecord,
        Duration lastTime) {
        this.trainingHistoryId = trainingHistoryId;
        this.distance = distance;
        this.personalRecord = personalRecord;
        this.lastTime = lastTime;
    }

    public Long getTrainingHistoryId() {
        return trainingHistoryId;
    }

    public Distance getDistance() {
        return distance;
    }

    public Duration getPersonalRecord() {
        return personalRecord;
    }

    public Duration getLastTime() {
        return lastTime;
    }
}
