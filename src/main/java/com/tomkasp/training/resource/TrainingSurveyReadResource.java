package com.tomkasp.training.resource;

import com.tomkasp.training.application.TrainingSurveyQueryService;
import com.tomkasp.training.application.data.TrainingSurveyReadData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api")
public class TrainingSurveyReadResource {

    TrainingSurveyQueryService trainingSurveyQueryService;

    @RequestMapping(value = "/trainingsurvey", method = RequestMethod.GET)
    public TrainingSurveyReadData getSurvey() {
        final TrainingSurveyReadData trainingSurveyReadData =
            trainingSurveyQueryService
                .getTrainingSurveyData();
        return trainingSurveyReadData;
    }

}
