package com.tomkasp.training.application;

import com.tomkasp.fitapp_common.service.UserService;
import com.tomkasp.training.application.data.TrainingSurveyMapper;
import com.tomkasp.training.application.data.TrainingSurveyReadData;
import com.tomkasp.training.domain.TrainingSurvey;
import com.tomkasp.training.domain.TrainingSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class TrainingSurveyQueryService {

    private final TrainingSurveyRepository trainingSurveyRepository;

    private final UserService userService;

    private final TrainingSurveyMapper trainingSurveyMapper;


    @Autowired
    public TrainingSurveyQueryService(
        TrainingSurveyRepository trainingSurveyRepository,
        UserService userService, TrainingSurveyMapper trainingSurveyMapper) {
        this.trainingSurveyRepository = trainingSurveyRepository;
        this.userService = userService;
        this.trainingSurveyMapper = trainingSurveyMapper;
    }

    public TrainingSurveyReadData getTrainingSurveyData() {
        final String username = userService.getUserWithAuthorities().getLogin();
        final Optional<TrainingSurvey> trainingSurveyOptional =
            trainingSurveyRepository
                .findByUsername(username);
        return trainingSurveyOptional.map(result ->
            trainingSurveyMapper.trainingSurveyToTrainingSurveyData(result)
        ).orElse(null);
    }
}
