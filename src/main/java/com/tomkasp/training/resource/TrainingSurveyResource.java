package com.tomkasp.training.resource;

import com.tomkasp.training.application.TrainingSurveyQueryService;
import com.tomkasp.training.application.data.TrainingSurveyData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api")
public class TrainingSurveyResource {

    TrainingSurveyQueryService trainingSurveyQueryService;

    @RequestMapping(value = "/trainingsurvey", method = RequestMethod.GET)
    public TrainingSurveyData getSurvey() {
        final TrainingSurveyData trainingSurveyData =
            trainingSurveyQueryService
                .getTrainingSurveyData();
        return trainingSurveyData;
    }

}
