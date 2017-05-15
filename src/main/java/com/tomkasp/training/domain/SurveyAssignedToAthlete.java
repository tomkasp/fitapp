package com.tomkasp.training.domain;

import com.tomkasp.common.domain.model.DomainEvent;

import java.util.Date;

/**
 * @author Tomasz Kasprzycki
 */
public class SurveyAssignedToAthlete implements DomainEvent {

    private final Long athleteId;
    private final TrainingSurveyId surveyId;
    private final BaseInformation baseInformation;
    private final HealthInformation healthInformation;
    private final NutritionInformation nutritionInformation;
    private final TrainingGoal trainingGoal;


    public SurveyAssignedToAthlete(
        Long athleteId,
        TrainingSurveyId surveyId,
        BaseInformation baseInformation,
        HealthInformation healthInformation,
        NutritionInformation nutritionInformation,
        TrainingGoal trainingGoal) {
        this.athleteId = athleteId;
        this.surveyId = surveyId;
        this.baseInformation = baseInformation;
        this.healthInformation = healthInformation;
        this.nutritionInformation = nutritionInformation;
        this.trainingGoal = trainingGoal;
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
