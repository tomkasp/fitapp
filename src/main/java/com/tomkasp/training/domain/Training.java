package com.tomkasp.training.domain;

import org.springframework.data.geo.Distance;

/**
 * @author Tomasz Kasprzycki
 */
public class Training {

    private Long id;

    public Training(Distance trainingDistance, RaceResult raceResult) {
        calculateTempo(raceResult);
    }

    private RunTempos calculateTempo(RaceResult raceResult) {
        final RunTempoCalculator runTempoCalculator = new RunTempoCalculator(raceResult);
        return runTempoCalculator.calculateRunTempo();

    }

    public Long getId() {
        return id;
    }


}
