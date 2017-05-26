package com.tomkasp.training.application.data;

import com.tomkasp.training.domain.HealthInformation;
import com.tomkasp.training.domain.NutritionInformation;
import com.tomkasp.training.domain.TrainingGoal;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingSurveyReadData {

    private Long id;

    private String username;
    //
//    private BaseInformation baseInformation;
//
    private HealthInformation healthInformation;
    //
    private NutritionInformation nutritionInformation;

    private TrainingGoal trainingGoal;


    public Long getId() {
        return id;
    }

    public TrainingSurveyReadData setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public TrainingSurveyReadData setUsername(String username) {
        this.username = username;
        return this;
    }

    //        public BaseInformation getBaseInformation() {
//        return baseInformation;
//    }
//
//    public TrainingSurveyReadData setBaseInformation(BaseInformation baseInformation) {
//        this.baseInformation = baseInformation;
//        return this;
//    }
//
    public HealthInformation getHealthInformation() {
        return healthInformation;
    }

    public TrainingSurveyReadData setHealthInformation(HealthInformation healthInformation) {
        this.healthInformation = healthInformation;
        return this;
    }

    public NutritionInformation getNutritionInformation() {
        return nutritionInformation;
    }

    public TrainingSurveyReadData setNutritionInformation(NutritionInformation nutritionInformation) {
        this.nutritionInformation = nutritionInformation;
        return this;
    }

    public TrainingGoal getTrainingGoal() {
        return trainingGoal;
    }

    public TrainingSurveyReadData setTrainingGoal(TrainingGoal trainingGoal) {
        this.trainingGoal = trainingGoal;
        return this;
    }
}
