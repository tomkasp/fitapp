package com.tomkasp.training.resource;

import com.tomkasp.training.application.data.TrainingSurveyWriteData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @PostMapping(value = "/trainingsurvey")
    public void createTrainingSurvey(@RequestBody TrainingSurveyWriteData trainingSurveyWriteData) {
        log.info("Data: {}", trainingSurveyWriteData);
    }

}
