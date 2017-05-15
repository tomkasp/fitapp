package com.tomkasp.training.domain;

import com.tomkasp.common.domain.model.DomainEventPublisher;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.geo.Distance;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.Duration;

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
    private TrainingGoal trainingGoal;

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
        this.trainingGoal = trainingGoals;
    }

    public TrainingHistory addTrainingHistoryToSurvey(Distance distance, Duration personalRecord, Duration lastTime) {
        DomainEventPublisher
            .instance()
            .publish(new TrainingHistoryAssignedToSurvey(
                distance,
                personalRecord,
                lastTime,
                new TrainingSurveyId(this.getId())
            ));
        return new TrainingHistory(
            distance,
            personalRecord,
            lastTime,
            new TrainingSurveyId(this.getId())
        );
    }

    public void removeTrainingHistoryFromSurvey(Long trainingHistoryId) {
        DomainEventPublisher
            .instance()
            .publish(new TrainingHistoryRemovedFromSurvey(
                trainingHistoryId,
                this.id
            ));
    }

    public TrainingIntensityPlan addTrainingDayToSurvey(DayOfWeek dayOfWeek, TrainingIntensity trainingIntensity) {
        return new TrainingIntensityPlan(
            dayOfWeek,
            trainingIntensity,
            new TrainingSurveyId(this.getId())
        );
    }

    public Long getId() {
        return id;
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

    public TrainingGoal getTrainingGoal() {
        return trainingGoal;
    }
}
