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

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FitappApp.class)
@Transactional
@ActiveProfiles({"dev"})
public class TrainingSurveyApplicationServiceTest extends EventTrackingTestCase {

    private SecurityContext securityContext;

    @Autowired
    TrainingSurveyApplicationService trainingSurveyApplicationService;

    @Autowired
    TrainingHistoryRepository trainingHistoryRepository;

    @Autowired
    TrainingIntensityPlanRepository trainingIntensityPlanRepository;

    @Before
    public void config() throws Exception {
        setUp();
        securityContext = SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.setContext(securityContext);
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));
    }

    @Test
    public void assignTrainingSurveyTest() throws Exception {
        HealthInformation healthInformation = createHealthInformation();
        BaseInformation baseInformation = createBaseInformation();
        boolean meat_acceptance = false;
        boolean dairiesAcceptance = true;
        boolean allergies = true;
        boolean foodIntolerance = true;
        final TrainingSurvey trainingSurvey = trainingSurveyApplicationService
            .assignTrainingSurveyToAthlete(
                new AssignTrainingSurveyToAthleteCommand(
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

        expectedEvent(SurveyAssignedToAthlete.class);

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

        expectedEvents(2);
        expectedEvent(SurveyAssignedToAthlete.class);
        expectedEvent(TrainingHistoryAssignedToSurvey.class);
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
            new RemoveTrainingHistoryCommandFromSurvey(
                trainingSurvey.getId(),
                addTrainingHistoryCommand.getResponse())
        );

        expectedEvent(TrainingHistoryRemovedFromSurvey.class);
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

        expectedEvents(3);
        expectedEvent(TrainingHistoryUpdated.class);
        assertEquals(newDistance, savedTrainingHistory.getDistance());
        assertEquals(newLastTime, savedTrainingHistory.getLastTime());
        assertEquals(newPersonalRecord, savedTrainingHistory.getPersonalRecord());
    }

    @Test
    public void addTrainingIntensityPlanToSurveyTest() {
        final TrainingSurvey trainingSurvey = createTrainingSurvey();

        final AddTrainingIntensityPlanCommand addTrainingIntensityPlanCommand = new AddTrainingIntensityPlanCommand(
            DayOfWeek.FRIDAY,
            TrainingIntensity.MEDIUM,
            new TrainingSurveyId(trainingSurvey.getId())
        );
        trainingSurveyApplicationService.addTrainingIntensityPlanToSurvey(
            addTrainingIntensityPlanCommand
        );

        final TrainingIntensityPlan trainingIntensityPlan = trainingIntensityPlanRepository.findOne(addTrainingIntensityPlanCommand.getResponse());

        expectedEvents(2);
        expectedEvent(TrainingIntensityPlanAssignedToSurvey.class);
        assertNotNull(trainingIntensityPlan);

    }

    @Test
    public void editTrainingIntensityPlanTest() {
        final TrainingSurvey trainingSurvey = createTrainingSurvey();

        final AddTrainingIntensityPlanCommand addTrainingIntensityPlanCommand = new AddTrainingIntensityPlanCommand(
            DayOfWeek.FRIDAY,
            TrainingIntensity.MEDIUM,
            new TrainingSurveyId(trainingSurvey.getId())
        );
        trainingSurveyApplicationService.addTrainingIntensityPlanToSurvey(
            addTrainingIntensityPlanCommand
        );

        final UpdateTrainingIntensityPlanCommand updateTrainingIntensityPlanCommand = new UpdateTrainingIntensityPlanCommand(
            addTrainingIntensityPlanCommand.getResponse(),
            DayOfWeek.SATURDAY,
            TrainingIntensity.SHORT
        );

        trainingSurveyApplicationService.updateSurveysTrainingIntensityPlan(updateTrainingIntensityPlanCommand);

        //next just reading if value has changed
        final TrainingIntensityPlan trainingIntensityPlan = trainingIntensityPlanRepository
            .findOne(addTrainingIntensityPlanCommand.getResponse());

        expectedEvents(3);
        expectedEvent(TrainingIntensityPlanUpdated.class);
        assertEquals(DayOfWeek.SATURDAY, trainingIntensityPlan.getDayOfWeek());
        assertEquals(TrainingIntensity.SHORT, trainingIntensityPlan.getTrainingIntensity());

    }

    @Test
    public void removeTrainingIntensityPlanFromSurveyTest() {
        final TrainingSurvey trainingSurvey = createTrainingSurvey();

        final AddTrainingIntensityPlanCommand addTrainingIntensityPlanCommand = new AddTrainingIntensityPlanCommand(
            DayOfWeek.FRIDAY,
            TrainingIntensity.MEDIUM,
            new TrainingSurveyId(trainingSurvey.getId())
        );
        trainingSurveyApplicationService.addTrainingIntensityPlanToSurvey(
            addTrainingIntensityPlanCommand
        );

        trainingSurveyApplicationService.removeTrainingIntensityPlanFromSurvey(
            new RemoveTrainingIntensityPlanCommand(
                addTrainingIntensityPlanCommand.getResponse()
            )
        );

        expectedEvent(TrainingIntensityPlanRemoved.class);
        assertNull(trainingIntensityPlanRepository.findOne(addTrainingIntensityPlanCommand.getResponse()));

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
                new AssignTrainingSurveyToAthleteCommand(
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
