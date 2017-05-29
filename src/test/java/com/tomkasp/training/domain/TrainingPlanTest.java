package com.tomkasp.training.domain;

import org.junit.Test;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingPlanTest {

    @Test
    public void predictResult() throws Exception {
        TrainingPlan trainingPlan = new TrainingPlan();
        Distance distance = new Distance(10, Metrics.KILOMETERS);
        Duration duration = Duration.ofSeconds(2340);
        final RaceResult raceResult = new RaceResult(distance, duration);
        final BigDecimal predictionResult = BigDecimal.valueOf(trainingPlan.calculateRacePredictor(raceResult));

        assertEquals(predictionResult, BigDecimal.valueOf(1122.339d));

    }

}
