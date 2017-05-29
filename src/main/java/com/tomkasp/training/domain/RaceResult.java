package com.tomkasp.training.domain;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;

import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
public class RaceResult {

    private final Distance distance;

    private final Duration duration;

    public RaceResult(Distance distance, Duration duration) {
        this.distance = distance;
        this.duration = duration;
    }

    public Long getTimeInSeconds() {
        return duration.getSeconds();
    }

    public double getDistanceInMeters() {
        if (distance.getMetric().equals(Metrics.KILOMETERS)) {
            return distance.getValue() * 1000;
        } else {
            //TODO convert to kilometers
            return 0d;
        }
    }
}
