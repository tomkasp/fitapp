package com.tomkasp.training.domain;

import com.tomkasp.common.common.domain.model.DomainEvent;
import org.springframework.data.geo.Distance;

import java.time.Duration;
import java.util.Date;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingHistoryUpdated implements DomainEvent {

    private Long id;
    private Distance distance;
    private Duration personalRecord;
    private Duration lastTime;
    private TrainingSurveyId trainingSurveyId;

    public TrainingHistoryUpdated(
        Long id,
        Distance distance,
        Duration personalRecord,
        Duration lastTime,
        TrainingSurveyId trainingSurveyId) {
        this.id = id;
        this.distance = distance;
        this.personalRecord = personalRecord;
        this.lastTime = lastTime;
        this.trainingSurveyId = trainingSurveyId;
    }

    @Override
    public int eventVersion() {
        return 0;
    }

    @Override
    public Date occurredOn() {
        return new Date();
    }
}
