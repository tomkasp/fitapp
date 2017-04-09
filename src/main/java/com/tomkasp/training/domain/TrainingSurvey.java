package com.tomkasp.training.domain;

import com.tomkasp.config.Constants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String name;


    @OneToOne
    @JoinColumn(name = "ATHLETE_ID", unique = true, nullable = false, updatable = false)
    private Athlete athlete;

    @Embedded
    private BaseInformation baseInformation;

    public TrainingSurvey(Athlete athlete, BaseInformation baseInformation) {
        this.athlete = athlete;
        this.baseInformation = baseInformation;
    }
//    private HealtCheckInformation healtCheckInformation;
//    private NutritionInformation nutritionInformation;
//    private TrainingGoal trainingGoal;
//    private TrainingPlan trainingPlan;
//    private PersonalRecords personalRecords;


    public Long getId() {
        return id;
    }

    public TrainingSurvey setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TrainingSurvey setName(String name) {
        this.name = name;
        return this;
    }
}
