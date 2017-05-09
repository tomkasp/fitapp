package com.tomkasp.training.domain;

import javax.persistence.*;
import java.time.DayOfWeek;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
public class TrainingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private DayOfWeek dayOfWeek;

    @Column
    private TrainingIntensity trainingIntensity;

    @Column
    private TrainingSurveyId trainingSurveyId;

    public TrainingDay(DayOfWeek dayOfWeek, TrainingIntensity trainingIntensity, TrainingSurveyId trainingSurveyId) {
        this.dayOfWeek = dayOfWeek;
        this.trainingIntensity = trainingIntensity;
        this.trainingSurveyId = trainingSurveyId;
    }

    public Long getId() {
        return id;
    }
}
