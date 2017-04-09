package com.tomkasp.training.application;

import com.tomkasp.service.UserService;
import com.tomkasp.training.application.command.CreateTrainingSurveyCommand;
import com.tomkasp.training.domain.Athlete;
import com.tomkasp.training.domain.AthleteRepository;
import com.tomkasp.training.domain.TrainingSurvey;
import com.tomkasp.training.domain.TrainingSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class TrainingSurveyApplicationService {

    private final TrainingSurveyRepository trainingSurveyRepository;

    private final UserService userService;

    private final AthleteRepository athleteRepository;

    @Autowired
    public TrainingSurveyApplicationService(TrainingSurveyRepository trainingSurveyRepository, UserService userService, AthleteRepository athleteRepository) {
        this.trainingSurveyRepository = trainingSurveyRepository;
        this.userService = userService;
        this.athleteRepository = athleteRepository;
    }

    @Transactional
    public TrainingSurvey createTrainingSurvey(CreateTrainingSurveyCommand createTrainingSurveyCommand) {
        Athlete athlete = this.athleteData();
        final TrainingSurvey trainingSurvey = athlete.assignSurvey(createTrainingSurveyCommand.getBaseInformation());
        trainingSurveyRepository.save(trainingSurvey);

        if(trainingSurvey == null){
            throw new IllegalArgumentException("survey not assigned");
        }

        return trainingSurvey;
    }

    private Athlete athleteData() {
        final Optional<Athlete> athlete = athleteRepository.findByUserId(userService.getUserWithAuthorities().getId());
        return athlete.get();
    }

}
