package com.tomkasp.training.domain.trainingplan;

import com.tomkasp.training.domain.RaceResult;
import com.tomkasp.training.domain.RunCategory;
import com.tomkasp.training.domain.RunTempoCalculator;
import com.tomkasp.training.domain.RunTempos;
import org.springframework.data.geo.Distance;
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
    private final RaceResult raceResult;

    @Column
    private Distance trainingDistance;

    @Column
    private RunCategory runCategory;

    public Training(Distance trainingDistance, RaceResult raceResult) {
        Assert.notNull(trainingDistance, "Training distance cannot be null");
        Assert.notNull(raceResult, "Race result cannot be null");
        final RunTempos runTempos = calculateTempo(raceResult);
        this.trainingDistance = trainingDistance;
        this.raceResult = raceResult;
    }

    public List<String> plan() {
        return TrainingPlanFactory.build(
            this.runCategory(),
            calculateTempo(this.raceResult()));
    }

    private RunTempos calculateTempo(RaceResult raceResult) {
        final RunTempoCalculator runTempoCalculator = new RunTempoCalculator(raceResult);
        return runTempoCalculator.calculateRunTempo();

    }

    public Long getId() {
        return id;
    }

    public RunCategory runCategory() {
        return this.runCategory;
    }

    public RaceResult raceResult() {
        return this.raceResult;
    }

}
