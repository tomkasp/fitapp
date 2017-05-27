package com.tomkasp.training.resource;

import com.tomkasp.common.domain.model.Height;
import com.tomkasp.common.domain.model.Weight;
import com.tomkasp.training.application.command.AssignTrainingSurveyToAthleteCommand;
import com.tomkasp.training.application.data.TrainingSurveyWriteData;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Distance;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
@RestController()
@RequestMapping("/api")
public class TrainingSurveyWriteResource {

    private final Logger log = LoggerFactory.getLogger(TrainingSurveyWriteResource.class);


    @PostMapping(value = "/trainingsurvey")
    public void createTrainingSurvey(@RequestBody TrainingSurveyWriteData trainingSurveyWriteData) {
        log.info("Data: {}", trainingSurveyWriteData);
        new AssignTrainingSurveyToAthleteCommand(
            new LocalDate(),
            new Weight(),
            new Height(),
            trainingSurveyWriteData.isHealthContraindications(),
            trainingSurveyWriteData.isStressTest(),
            trainingSurveyWriteData.isBloodTest(),
            Duration.ofHours(trainingSurveyWriteData.getHoursOfSleep()),
            Duration.ofHours(),
            new Distance(),
            trainingSurveyWriteData.getRunCategory(),
            trainingSurveyWriteData.isMeatAcceptance(),
            trainingSurveyWriteData.isDairiesAcceptance(),
            trainingSurveyWriteData.isAllergies(),
            trainingSurveyWriteData.isFoodIntolerance()
        );
    }

}
