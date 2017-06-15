package com.tomkasp.training.domain;

import com.tomkasp.common.common.domain.model.DomainEventPublisher;
import com.tomkasp.training.domain.survey.TrainingSurveyId;
import org.springframework.data.geo.Distance;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
public class TrainingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Distance distance;

    @Column
    private Duration personalRecord;

    @Column
    private Duration lastTime;

    @Embedded
    private TrainingSurveyId trainingSurveyId;

    public TrainingHistory(
        Distance distance,
        Duration personalRecord,
        Duration lastTime,
        TrainingSurveyId trainingSurveyId) {
        this.distance = distance;
        this.personalRecord = personalRecord;
        this.lastTime = lastTime;
        this.trainingSurveyId = trainingSurveyId;
    }

    public void updateTrainingHistory(Distance distance, Duration personalRecord, Duration lastTime) {
        Assert.notNull(distance);
        Assert.notNull(personalRecord);
        Assert.notNull(lastTime);
        Assert.notNull(this.id);
        Assert.notNull(this.trainingSurveyId);
        this.distance = distance;
        this.personalRecord = personalRecord;
        this.lastTime = lastTime;
        DomainEventPublisher
            .instance()
            .publish(new TrainingHistoryUpdated(
                this.id,
                distance,
                personalRecord,
                lastTime,
                this.trainingSurveyId
            ));
    }

    public Long getId() {
        return id;
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

    public TrainingSurveyId getTrainingSurveyId() {
        return trainingSurveyId;
    }
}

