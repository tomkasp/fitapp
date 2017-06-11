package com.tomkasp.training.application;

import com.tomkasp.FitappApp;
import com.tomkasp.training.application.command.CalculateAthleteTrainingCommand;
import com.tomkasp.training.domain.RaceResult;
import com.tomkasp.training.domain.TrainingRepository;
import com.tomkasp.training.domain.trainingplan.Training;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
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

    @Test
    public void calculateTrainingTest() {
        Distance trainingDistance = new Distance(21, Metrics.KILOMETERS);
        RaceResult lastRaceResult = new RaceResult(
            new Distance(21, Metrics.KILOMETERS),
            Duration.ofSeconds(1155)
        );
        final CalculateAthleteTrainingCommand calculateAthleteTrainingCommand =
            new CalculateAthleteTrainingCommand(
                trainingDistance,
                lastRaceResult
            );

        athleteApplicationService.calculateTraining(calculateAthleteTrainingCommand);

        final Long trainingId = calculateAthleteTrainingCommand.getResponse();
        final Training savedTraining = trainingRepository.getOne(trainingId);


        Assert.assertNotNull(savedTraining);

    }
}