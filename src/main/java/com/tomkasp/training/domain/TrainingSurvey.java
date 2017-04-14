package com.tomkasp.training.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

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


    private List<TrainingDays> trainingDays;

    private List<TrainingHistory> trainingHistories;



    public TrainingSurvey(Athlete athlete, BaseInformation baseInformation) {
        this.athlete = athlete;
        this.baseInformation = baseInformation;
    }


    public Long getId() {
        return id;
    }

    public TrainingSurvey setId(Long id) {
        this.id = id;
        return this;
    }

}
