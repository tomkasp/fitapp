package com.tomkasp.training.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingPlanTest {

    @Test
    public void predictResult() throws Exception {
        TrainingPlan trainingPlan = new TrainingPlan();

        final RaceResult raceResult = new RaceResult();

        final BigDecimal predictionResult = BigDecimal.valueOf(trainingPlan.calculateRacePredictor(raceResult));

        assertEquals(predictionResult, BigDecimal.valueOf(1122.339d));

    }

}
