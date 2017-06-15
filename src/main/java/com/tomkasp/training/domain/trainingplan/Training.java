package com.tomkasp.training.domain.trainingplan;

import com.tomkasp.training.domain.RaceResult;
import com.tomkasp.training.domain.RunCategory;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private RunCategory trainingRunCategory;

    public Training(RunCategory trainingRunCategory, RaceResult raceResult) {
        Assert.notNull(trainingRunCategory, "Training distance cannot be null");
        Assert.notNull(raceResult, "Race result cannot be null");
        this.trainingRunCategory = trainingRunCategory;
    }

    public List<String> plan(RaceResult raceResult) {
        return TrainingPlanFactory.build(
            this.runCategory(),
            calculateTempo(raceResult));
    }

    private RunTempos calculateTempo(RaceResult raceResult) {
        final RunTempoCalculator runTempoCalculator = new RunTempoCalculator(raceResult);
        return runTempoCalculator.calculateRunTempo();
    }

    public Long getId() {
        return id;
    }

    public RunCategory runCategory() {
        return this.trainingRunCategory;
    }
}
