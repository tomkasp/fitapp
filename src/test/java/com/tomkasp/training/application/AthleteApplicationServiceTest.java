package com.tomkasp.training.application;

import com.tomkasp.FitappApp;
import com.tomkasp.training.application.command.CalculateAthleteTrainingCommand;
import com.tomkasp.training.domain.RaceResult;
import com.tomkasp.training.domain.RunCategory;
import com.tomkasp.training.domain.trainingplan.Training;
import com.tomkasp.training.domain.trainingplan.TrainingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FitappApp.class)
@Transactional
@ActiveProfiles({"dev"})
public class AthleteApplicationServiceTest {

    @Autowired
    AthleteApplicationService athleteApplicationService;

    @Autowired
    TrainingRepository trainingRepository;

    private SecurityContext securityContext;

    @Before
    public void config() throws Exception {
        securityContext = SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.setContext(securityContext);
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));
    }

    @Test
    public void calculateTrainingTest() {
        RaceResult lastRaceResult = new RaceResult(
            new Distance(21, Metrics.KILOMETERS),
            Duration.ofSeconds(1155)
        );
        final CalculateAthleteTrainingCommand calculateAthleteTrainingCommand =
            new CalculateAthleteTrainingCommand(
                RunCategory.FIVE_K,
                lastRaceResult
            );

        athleteApplicationService.calculateTraining(calculateAthleteTrainingCommand);
        final Long trainingId = calculateAthleteTrainingCommand.getResponse();
        final Training savedTraining = trainingRepository.getOne(trainingId);
        Assert.assertNotNull(savedTraining);
    }
}
