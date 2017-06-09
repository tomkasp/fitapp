package com.tomkasp.training.domain;

import org.springframework.data.geo.Distance;
import org.springframework.util.Assert;

import javax.persistence.*;

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

    public Training(Distance trainingDistance, RaceResult raceResult) {
        Assert.notNull(trainingDistance, "Training distance cannot be null");
        Assert.notNull(raceResult, "Race result cannot be null");
        final RunTempos runTempos = calculateTempo(raceResult);
        this.trainingDistance = trainingDistance;
        this.raceResult = raceResult;
    }

    private RunTempos calculateTempo(RaceResult raceResult) {
        final RunTempoCalculator runTempoCalculator = new RunTempoCalculator(raceResult);
        return runTempoCalculator.calculateRunTempo();

    }

    public void providePlan() {

    }

    public Long getId() {
        return id;
    }


}
