package com.tomkasp.training.application;

import com.tomkasp.service.UserService;
import com.tomkasp.training.application.command.AddTrainingHistoryCommand;
import com.tomkasp.training.application.command.CreateTrainingSurveyCommand;
import com.tomkasp.training.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private final TrainingHistoryRepository trainingHistoryRepository;

    @Autowired
    public TrainingSurveyApplicationService(TrainingSurveyRepository trainingSurveyRepository, UserService userService, AthleteRepository athleteRepository, TrainingHistoryRepository trainingHistoryRepository) {
        this.trainingSurveyRepository = trainingSurveyRepository;
        this.userService = userService;
        this.athleteRepository = athleteRepository;
        this.trainingHistoryRepository = trainingHistoryRepository;
    }

    @Transactional
    public TrainingSurvey assignTrainingSurvey(CreateTrainingSurveyCommand createTrainingSurveyCommand) {
        Athlete athlete = this.athleteData();
        final TrainingSurvey trainingSurvey = athlete
            .assignSurvey(
                createTrainingSurveyCommand.getBaseInformation(),
                createTrainingSurveyCommand.getHealthInformation(),
                createTrainingSurveyCommand.getNutritionInformation(),
                createTrainingSurveyCommand.getTrainingGoal());
        trainingSurveyRepository.save(trainingSurvey);

        if (trainingSurvey == null) {
            throw new IllegalArgumentException("survey not assigned");
        }

        return trainingSurvey;
    }

    @Transactional
    public void addTrainingHistory(AddTrainingHistoryCommand addTrainingHistoryCommand) {
        final TrainingSurvey trainingSurvey = trainingSurveyRepository.getOne(
            addTrainingHistoryCommand.getTrainingSurveyId().getTraininSurveyId());
        final TrainingHistory trainingHistory = trainingSurvey.addTraingHistory(
            addTrainingHistoryCommand.getDistance(),
            addTrainingHistoryCommand.getPersonalRecord(),
            addTrainingHistoryCommand.getLastTime());
        trainingHistoryRepository.save(trainingHistory);
    }

    private Athlete athleteData() {
        final Optional<Athlete> athlete = athleteRepository.findByUserId(userService.getUserWithAuthorities().getId());
        return athlete.get();
    }

}
