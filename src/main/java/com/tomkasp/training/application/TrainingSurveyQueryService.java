package com.tomkasp.training.application;

import com.tomkasp.training.application.data.TrainingSurveyData;
import com.tomkasp.training.domain.TrainingSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class TrainingSurveyQueryService {


    private final TrainingSurveyRepository trainingSurveyRepository;

    @Autowired
    public TrainingSurveyQueryService(TrainingSurveyRepository trainingSurveyRepository) {
        this.trainingSurveyRepository = trainingSurveyRepository;
    }

    public TrainingSurveyData getTrainingSurveyData() {
        return null;
    }
}
