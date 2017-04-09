package com.tomkasp.training.application;

import com.tomkasp.FitappApp;
import com.tomkasp.training.application.command.CreateTrainingSurveyCommand;
import com.tomkasp.training.domain.TrainingSurvey;
import com.tomkasp.training.domain.TrainingSurveyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FitappApp.class)
@Transactional
@ActiveProfiles({"dev"})
public class TrainingSurveyApplicationServiceTest {

    @Autowired
    TrainingSurveyApplicationService trainingSurveyApplicationService;

    @Test
    public void createTraining() throws Exception {
        final TrainingSurvey trainingSurvey = trainingSurveyApplicationService.createTrainingSurvey(new CreateTrainingSurveyCommand());
        assertNotNull(trainingSurvey);
    }

}
