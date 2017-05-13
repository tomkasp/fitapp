package com.tomkasp.training.domain;

import javax.persistence.*;
import java.time.DayOfWeek;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
public class TrainingIntensityPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private DayOfWeek dayOfWeek;

    @Column
    private TrainingIntensity trainingIntensity;

    @Column
    private TrainingSurveyId trainingSurveyId;

    public TrainingIntensityPlan(DayOfWeek dayOfWeek, TrainingIntensity trainingIntensity, TrainingSurveyId trainingSurveyId) {
        this.dayOfWeek = dayOfWeek;
        this.trainingIntensity = trainingIntensity;
        this.trainingSurveyId = trainingSurveyId;
    }

    public void updateTrainingIntensityPlan(DayOfWeek dayOfWeek, TrainingIntensity trainingIntensity) {
        this.dayOfWeek = dayOfWeek;
        this.trainingIntensity = trainingIntensity;
    }

    public Long getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TrainingIntensity getTrainingIntensity() {
        return trainingIntensity;
    }

    public TrainingSurveyId getTrainingSurveyId() {
        return trainingSurveyId;
    }
}
