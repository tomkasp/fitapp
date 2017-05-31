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
public class RunTempoCalculatorTest {

    @Test
    public void calculateLonRunMaxTempo() throws Exception {
        RunTempoCalculator trainingPlan = new RunTempoCalculator(raceResult);
        double tempoInSeconds = trainingPlan.calculateLonRunMaxTempo(1143);
        assertEquals(BigDecimal.valueOf(203.8008475d), BigDecimal.valueOf(tempoInSeconds));
    }

    @Test
    public void calculateMaximumOxygenSpeedFormTempo() throws Exception {
        RunTempoCalculator trainingPlan = new RunTempoCalculator(raceResult);
        double tempoInSeconds = trainingPlan.calculateMaximumOxygenSpeedFormTempo(1143);
        assertEquals(BigDecimal.valueOf(221.2130584d), BigDecimal.valueOf(tempoInSeconds));
    }


    @Test
    public void calculateEasyTemp() throws Exception {
        RunTempoCalculator trainingPlan = new RunTempoCalculator(raceResult);
        double tempoInSeconds = trainingPlan.calculateEasyTempo(1143);
        assertEquals(BigDecimal.valueOf(295.3963415d), BigDecimal.valueOf(tempoInSeconds));
    }


    @Test
    public void riegelPredictorResultTest() throws Exception {
        RunTempoCalculator trainingPlan = new RunTempoCalculator(raceResult);
        Distance distance = new Distance(21, Metrics.KILOMETERS);
        Duration duration = Duration.ofSeconds(5230);
        final RaceResult raceResult = new RaceResult(distance, duration);
        final BigDecimal predictionResult = BigDecimal.valueOf(trainingPlan.calculateRiegelRacePredictor(raceResult));

        assertEquals(predictionResult, BigDecimal.valueOf(1122.339d));
    }

}
