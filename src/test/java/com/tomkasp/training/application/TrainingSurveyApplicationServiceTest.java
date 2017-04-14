package com.tomkasp.training.application;

import com.tomkasp.FitappApp;
import com.tomkasp.training.application.command.CreateTrainingSurveyCommand;
import com.tomkasp.training.domain.TrainingSurvey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));
        SecurityContextHolder.setContext(securityContext);
        final TrainingSurvey trainingSurvey = trainingSurveyApplicationService.assignTrainingSurvey(new CreateTrainingSurveyCommand(new Date(), "65", "172"));

        assertNotNull(trainingSurvey);
    }

}
