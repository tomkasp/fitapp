package com.tomkasp.training.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "training_survey")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TrainingSurvey {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ATHLETE_ID", unique = true, nullable = false, updatable = false)
    private Athlete athlete;

    @Embedded
    private BaseInformation baseInformation;

    @Embedded
    private HealthInformation healthInformation;

    @Embedded
    private NutritionInformation nutritionInformation;

    @Embedded
    private TrainingGoal trainingGoals;

//
//    private List<TrainingDays> trainingDays;
//
//    private List<TrainingHistory> trainingHistories;



    public TrainingSurvey(
        Athlete athlete,
        BaseInformation baseInformation, HealthInformation healthInformation, NutritionInformation nutritionInformation, TrainingGoal trainingGoals) {
        this.athlete = athlete;
        this.baseInformation = baseInformation;
        this.healthInformation = healthInformation;
        this.nutritionInformation = nutritionInformation;
        this.trainingGoals = trainingGoals;
    }


    public Long getId() {
        return id;
    }

    public TrainingSurvey setId(Long id) {
        this.id = id;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public BaseInformation getBaseInformation() {
        return baseInformation;
    }

    public HealthInformation getHealthInformation() {
        return healthInformation;
    }

    public NutritionInformation getNutritionInformation() {
        return nutritionInformation;
    }

    public TrainingGoal getTrainingGoals() {
        return trainingGoals;
    }
}
