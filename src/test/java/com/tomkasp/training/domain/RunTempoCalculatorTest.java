package com.tomkasp.training.domain;

import com.tomkasp.training.domain.trainingplan.RunTempoCalculator;
import com.tomkasp.training.domain.trainingplan.RunTempos;
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
    public void calculateRunTempos() {
        final RaceResult raceResult = new RaceResult(
            new Distance(21, Metrics.KILOMETERS),
            Duration.ofSeconds(5230)
        );
        RunTempoCalculator runTempoCalculator = new RunTempoCalculator(raceResult);
        final RunTempos runTempos = runTempoCalculator.calculateRunTempo();

        assertEquals(BigDecimal.valueOf(295), BigDecimal.valueOf(runTempos.easy()));
        assertEquals(BigDecimal.valueOf(221), BigDecimal.valueOf(runTempos.maximumOxygen()));
        assertEquals(BigDecimal.valueOf(203), BigDecimal.valueOf(runTempos.longRunMax()));
        assertEquals(BigDecimal.valueOf(333), BigDecimal.valueOf(runTempos.longRunMin()));
        assertEquals(BigDecimal.valueOf(182), BigDecimal.valueOf(runTempos.yasso800()));
    }

}
