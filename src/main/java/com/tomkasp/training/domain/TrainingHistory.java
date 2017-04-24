package com.tomkasp.training.domain;

import org.springframework.data.geo.Distance;

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


}

