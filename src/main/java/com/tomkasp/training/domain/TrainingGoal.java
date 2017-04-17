package com.tomkasp.training.domain;

import org.springframework.data.geo.Distance;

import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingGoal {

    private Distance distance;
    private Duration duration;
    private RUN_CATEGORY runCategory;

    protected TrainingGoal() {
        super();
    }

    public TrainingGoal(Distance distance, Duration duration, RUN_CATEGORY runCategory) {
        this.distance = distance;
        this.duration = duration;
        this.runCategory = runCategory;
    }
}
