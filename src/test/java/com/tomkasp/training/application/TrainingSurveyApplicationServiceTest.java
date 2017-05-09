package com.tomkasp.training.application;

import com.tomkasp.FitappApp;
import com.tomkasp.common.domain.model.Height;
import com.tomkasp.common.domain.model.HeightMetrics;
import com.tomkasp.common.domain.model.Weight;
import com.tomkasp.common.domain.model.WeightMetrics;
import com.tomkasp.training.application.command.*;
import com.tomkasp.training.domain.*;
import org.joda.time.LocalDate;
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

import java.time.DayOfWeek;
import java.time.Duration;

import static org.junit.Assert.*;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FitappApp.class)
@Transactional
@ActiveProfiles({"dev"})
public class TrainingSurveyApplicationServiceTest {

    private SecurityContext securityContext;

    @Autowired
    TrainingSurveyApplicationService trainingSurveyApplicationService;

    @Autowired
    TrainingHistoryRepository trainingHistoryRepository;

    @Autowired
    TrainingDayRepository trainingDayRepository;

    @Before
    public void setUp() {
        securityContext = SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.setContext(securityContext);
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));
    }

    @Test
    public void createTrainingTest() throws Exception {
        HealthInformation healthInformation = createHealthInformation();
        BaseInformation baseInformation = createBaseInformation();
        boolean meat_acceptance = false;
        boolean dairiesAcceptance = true;
        boolean allergies = true;
        boolean foodIntolerance = true;
        final TrainingSurvey trainingSurvey = trainingSurveyApplicationService
            .assignTrainingSurveyToAthlete(
                new CreateTrainingSurveyCommand(
                    createBaseInformation().getBirthday(),
                    createBaseInformation().getWeight(),
                    createBaseInformation().getHeight(),
                    healthInformation.getHealthContraindications(),
                    healthInformation.getStressTest(),
                    healthInformation.getBloodTest(),
                    healthInformation.getHoursOfSleep(),
                    Duration.ofHours(5L),
                    new Distance(25, Metrics.KILOMETERS),
                    RunCategory.MARATHON,
                    meat_acceptance,
                    dairiesAcceptance,
                    allergies,
                    foodIntolerance
                ));

        assertNotNull(trainingSurvey);
        assertEquals(trainingSurvey.getHealthInformation(), healthInformation);
        assertEquals(trainingSurvey.getBaseInformation(), baseInformation);
        assertNotNull(trainingSurvey.getId());

    }

    @Test
    public void assigningTrainingHistoryTest() {
        final TrainingSurvey trainingSurvey = createTrainingSurvey();
        trainingSurveyApplicationService.addTrainingHistory(
            new AddTrainingHistoryCommand(
                new TrainingSurveyId(trainingSurvey.getId()),
                new Distance(25, Metrics.KILOMETERS),
                Duration.ofSeconds(120),
                Duration.ofSeconds(150)
            )
        );
        //TODO check number of events.
    }

    @Test
    public void removeTrainingHistoryTest() {
        final TrainingSurvey trainingSurvey = createTrainingSurvey();

        final AddTrainingHistoryCommand addTrainingHistoryCommand = new AddTrainingHistoryCommand(
            new TrainingSurveyId(trainingSurvey.getId()),
            new Distance(25, Metrics.KILOMETERS),
            Duration.ofSeconds(120),
            Duration.ofSeconds(150)
        );

        trainingSurveyApplicationService.addTrainingHistory(
            addTrainingHistoryCommand
        );

        assertTrue(trainingHistoryRepository.findAll().size() > 0);

        trainingSurveyApplicationService.removeTrainingHistoryFromSurvey(
            new RemoveTrainingHistoryCommand(addTrainingHistoryCommand.getResponse())
        );

        assertTrue(trainingHistoryRepository.findAll().size() == 0);
    }

    @Test
    public void updateTrainingHistoryTest() {
        final TrainingSurvey trainingSurvey = createTrainingSurvey();

        final AddTrainingHistoryCommand addTrainingHistoryCommand = new AddTrainingHistoryCommand(
            new TrainingSurveyId(trainingSurvey.getId()),
            new Distance(25, Metrics.KILOMETERS),
            Duration.ofSeconds(120),
            Duration.ofSeconds(150)
        );

        trainingSurveyApplicationService.addTrainingHistory(
            addTrainingHistoryCommand
        );

        final Long trainingHistoryId = addTrainingHistoryCommand.getResponse();
        final Distance newDistance = new Distance(30, Metrics.KILOMETERS);
        final Duration newPersonalRecord = Duration.ofSeconds(120);
        final Duration newLastTime = Duration.ofSeconds(50);
        final UpdateTrainingHistoryCommand updateTrainingHistoryCommand
            = new UpdateTrainingHistoryCommand(
            trainingHistoryId,
            newDistance,
            newPersonalRecord,
            newLastTime
        );

        trainingSurveyApplicationService.updateSurveysTrainingHistory(
            updateTrainingHistoryCommand
        );

        final TrainingHistory savedTrainingHistory = trainingHistoryRepository.getOne(trainingHistoryId);

        assertEquals(newDistance, savedTrainingHistory.getDistance());
        assertEquals(newLastTime, savedTrainingHistory.getLastTime());
        assertEquals(newPersonalRecord, savedTrainingHistory.getPersonalRecord());
    }

    @Test
    public void addTrainingDayToSurveyTest() {
        final TrainingSurvey trainingSurvey = createTrainingSurvey();

        final AddTrainingDaysCommand addTrainingDaysCommand = new AddTrainingDaysCommand(
            DayOfWeek.FRIDAY,
            TrainingIntensity.MEDIUM,
            new TrainingSurveyId(trainingSurvey.getId())
        );
        trainingSurveyApplicationService.addTrainingDaysToSurvey(
            addTrainingDaysCommand
        );

        final TrainingDay trainingDay = trainingDayRepository.getOne(addTrainingDaysCommand.getResponse());

        assertNotNull(trainingDay);

    }

    public void removeTrainingDayFromSurveyTest() {

    }

    public void editTrainingDayTest() {

    }


    //Factory functions

    private TrainingSurvey createTrainingSurvey() {
        HealthInformation healthInformation = createHealthInformation();
        BaseInformation baseInformation = createBaseInformation();
        boolean meat_acceptance = false;
        boolean dairiesAcceptance = true;
        boolean allergies = true;
        boolean foodIntolerance = true;
        return trainingSurveyApplicationService
            .assignTrainingSurveyToAthlete(
                new CreateTrainingSurveyCommand(
                    createBaseInformation().getBirthday(),
                    createBaseInformation().getWeight(),
                    createBaseInformation().getHeight(),
                    healthInformation.getHealthContraindications(),
                    healthInformation.getStressTest(),
                    healthInformation.getBloodTest(),
                    healthInformation.getHoursOfSleep(),
                    Duration.ofHours(5L),
                    new Distance(25, Metrics.KILOMETERS),
                    RunCategory.MARATHON,
                    meat_acceptance,
                    dairiesAcceptance,
                    allergies,
                    foodIntolerance
                ));
    }

    private HealthInformation createHealthInformation() {
        boolean healthContraindications = false;
        boolean stressTest = false;
        boolean bloodTest = false;
        final HealthInformation healthInformation = new HealthInformation(
            healthContraindications,
            stressTest,
            bloodTest,
            Duration.ofHours(8L)
        );
        return healthInformation;
    }

    private BaseInformation createBaseInformation() {
        BaseInformation baseInformation = new BaseInformation(
            LocalDate.now(),
            new Weight(60D, WeightMetrics.KILOGRAM),
            new Height(172, HeightMetrics.CENTIMETER)
        );
        return baseInformation;
    }

}
