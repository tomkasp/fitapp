package com.tomkasp.training.domain;

import com.tomkasp.common.common.domain.model.DomainEvent;
import com.tomkasp.training.domain.survey.TrainingSurveyId;

import java.time.DayOfWeek;
import java.util.Date;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingIntensityPlanAssignedToSurvey implements DomainEvent {

    private final DayOfWeek dayOfWeek;
    private final TrainingIntensity trainingIntensity;
    private final TrainingSurveyId trainingSurveyId;

    public TrainingIntensityPlanAssignedToSurvey(
        DayOfWeek dayOfWeek,
        TrainingIntensity trainingIntensity,
        TrainingSurveyId trainingSurveyId) {
        this.dayOfWeek = dayOfWeek;
        this.trainingIntensity = trainingIntensity;
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
