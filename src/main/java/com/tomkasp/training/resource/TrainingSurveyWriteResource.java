package com.tomkasp.training.resource;

import com.tomkasp.training.application.TrainingSurveyApplicationService;
import com.tomkasp.training.application.command.AssignTrainingSurveyToAthleteCommand;
import com.tomkasp.training.application.data.TrainingSurveyWriteData;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController()
@RequestMapping("/api")
public class TrainingSurveyWriteResource {

    private final Logger log = LoggerFactory.getLogger(TrainingSurveyWriteResource.class);

    private final TrainingSurveyApplicationService trainingSurveyApplicationService;

    @Autowired
    public TrainingSurveyWriteResource(TrainingSurveyApplicationService trainingSurveyApplicationService) {
        this.trainingSurveyApplicationService = trainingSurveyApplicationService;
    }


    @PostMapping(value = "/trainingsurvey")
    public void createTrainingSurvey(@RequestBody TrainingSurveyWriteData trainingSurveyWriteData) {
        log.info("Data to write: {}", trainingSurveyWriteData);
        trainingSurveyApplicationService.assignTrainingSurveyToAthlete(
            new AssignTrainingSurveyToAthleteCommand(
                new LocalDate(),
                trainingSurveyWriteData.getWeight(),
                trainingSurveyWriteData.getHeight(),
                trainingSurveyWriteData.isHealthContraindications(),
                trainingSurveyWriteData.isStressTest(),
                trainingSurveyWriteData.isBloodTest(),
                trainingSurveyWriteData.getHoursOfSleep(),
                trainingSurveyWriteData.getDuration(),
                trainingSurveyWriteData.getDistance(),
                trainingSurveyWriteData.getRunCategory(),
                trainingSurveyWriteData.isMeatAcceptance(),
                trainingSurveyWriteData.isDairiesAcceptance(),
                trainingSurveyWriteData.isAllergies(),
                trainingSurveyWriteData.isFoodIntolerance(),
                trainingSurveyWriteData.getMeasureType())
        );
    }

}
