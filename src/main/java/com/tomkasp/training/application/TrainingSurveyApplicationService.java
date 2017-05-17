package com.tomkasp.training.application;

import com.tomkasp.service.UserService;
import com.tomkasp.training.application.command.*;
import com.tomkasp.training.domain.*;
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

    private final TrainingHistoryRepository trainingHistoryRepository;

    private final TrainingIntensityPlanRepository trainingIntensityPlanRepository;

    @Autowired
    public TrainingSurveyApplicationService(TrainingSurveyRepository trainingSurveyRepository, UserService userService, AthleteRepository athleteRepository, TrainingHistoryRepository trainingHistoryRepository, TrainingIntensityPlanRepository trainingIntensityPlanRepository) {
        this.trainingSurveyRepository = trainingSurveyRepository;
        this.userService = userService;
        this.athleteRepository = athleteRepository;
        this.trainingHistoryRepository = trainingHistoryRepository;
        this.trainingIntensityPlanRepository = trainingIntensityPlanRepository;
    }

    @Transactional
    public TrainingSurvey assignTrainingSurveyToAthlete(
        AssignTrainingSurveyToAthleteCommand assignTrainingSurveyToAthleteCommand
    ) {
        Athlete athlete = this.athleteData();
        final TrainingSurvey trainingSurvey = athlete
            .assignSurvey(
                assignTrainingSurveyToAthleteCommand.getBaseInformation(),
                assignTrainingSurveyToAthleteCommand.getHealthInformation(),
                assignTrainingSurveyToAthleteCommand.getNutritionInformation(),
                assignTrainingSurveyToAthleteCommand.getTrainingGoal());
        trainingSurveyRepository.save(trainingSurvey);

        if (trainingSurvey == null) {
            throw new IllegalArgumentException("survey not assigned");
        }
        assignTrainingSurveyToAthleteCommand.setResponse(trainingSurvey.getId());
        return trainingSurvey;
    }

    @Transactional
    public void addTrainingHistory(AddTrainingHistoryCommand addTrainingHistoryCommand) {
        final TrainingSurvey trainingSurvey =
            trainingSurveyRepository.getOne(
                addTrainingHistoryCommand.getTrainingSurveyId().getTrainingSurveyId());

        final TrainingHistory trainingHistory = trainingSurvey.addTrainingHistoryToSurvey(
            addTrainingHistoryCommand.getDistance(),
            addTrainingHistoryCommand.getPersonalRecord(),
            addTrainingHistoryCommand.getLastTime());

        trainingHistoryRepository.save(trainingHistory);
        addTrainingHistoryCommand.setResponse(trainingHistory.getId());
    }

    @Transactional
    public void updateSurveysTrainingHistory(UpdateTrainingHistoryCommand updateTrainingHistoryCommand) {
        final TrainingHistory trainingHistory = trainingHistoryRepository
            .findOne(updateTrainingHistoryCommand.getTrainingHistoryId());
        Assert.notNull(trainingHistory);
        trainingHistory.updateTrainingHistory(
            updateTrainingHistoryCommand.getDistance(),
            updateTrainingHistoryCommand.getPersonalRecord(),
            updateTrainingHistoryCommand.getLastTime()
        );
        trainingHistoryRepository.save(trainingHistory);
    }


    @Transactional
    public void removeTrainingHistoryFromSurvey(RemoveTrainingHistoryCommandFromSurvey removeTrainingHistoryCommandFromSurvey) {
        final TrainingSurvey trainingSurvey =
            trainingSurveyRepository.getOne(
                removeTrainingHistoryCommandFromSurvey.getSurveyId());

        final TrainingHistory trainingHistory =
            trainingHistoryRepository.
                findOne(
                    removeTrainingHistoryCommandFromSurvey.getTrainingHistoryId()
                );

        trainingSurvey
            .removeTrainingHistoryFromSurvey(
                removeTrainingHistoryCommandFromSurvey.getTrainingHistoryId());
        trainingHistoryRepository.delete(removeTrainingHistoryCommandFromSurvey.getTrainingHistoryId());
    }

    @Transactional
    public void addTrainingIntensityPlanToSurvey(AddTrainingIntensityPlanCommand addTrainingIntensityPlanCommand) {
        final TrainingSurvey trainingSurvey =
            trainingSurveyRepository.getOne(
                addTrainingIntensityPlanCommand.getTrainingSurveyId().getTrainingSurveyId());

        final TrainingIntensityPlan trainingIntensityPlan = trainingSurvey.addTrainingIntensityPlanToSurvey(
            addTrainingIntensityPlanCommand.getDayOfWeek(),
            addTrainingIntensityPlanCommand.getTrainingIntensity()
        );

        trainingIntensityPlanRepository.save(trainingIntensityPlan);
        addTrainingIntensityPlanCommand.setResponse(trainingIntensityPlan.getId());
    }

    @Transactional
    public void updateSurveysTrainingIntensityPlan(UpdateTrainingIntensityPlanCommand updateTrainingIntensityPlanCommand) {
        final TrainingIntensityPlan trainingIntensityPlan = trainingIntensityPlanRepository
            .findOne(updateTrainingIntensityPlanCommand.getTrainingIntensityPlanId());
        trainingIntensityPlan.updateTrainingIntensityPlan(
            updateTrainingIntensityPlanCommand.getDayOfWeek(),
            updateTrainingIntensityPlanCommand.getTrainingIntensity()
        );
        trainingIntensityPlanRepository.save(trainingIntensityPlan);
    }

    @Transactional
    public void removeTrainingIntensityPlanFromSurvey(RemoveTrainingIntensityPlanCommand removeTrainingIntensityPlanCommand) {
        final TrainingIntensityPlan trainingIntensityPlan =
            trainingIntensityPlanRepository
                .findOne(removeTrainingIntensityPlanCommand.getTrainingDayId());
        trainingIntensityPlan.delete();
        trainingIntensityPlanRepository.delete(removeTrainingIntensityPlanCommand.getTrainingDayId());
    }

    private Athlete athleteData() {
        final Optional<Athlete> athlete = athleteRepository.findByUserId(userService.getUserWithAuthorities().getId());
        return athlete.get();
    }
}
