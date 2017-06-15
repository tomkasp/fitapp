package com.tomkasp.training.application;

import com.tomkasp.FitappApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FitappApp.class)
@Transactional
@ActiveProfiles({"dev"})
public class AthleteQueryServiceTest {

    private final AthleteQueryService athleteQueryService;

    @Autowired
    public AthleteQueryServiceTest(AthleteQueryService athleteQueryService) {
        this.athleteQueryService = athleteQueryService;
    }


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAthleteTrainingPlanTest() throws Exception {
    }

}
