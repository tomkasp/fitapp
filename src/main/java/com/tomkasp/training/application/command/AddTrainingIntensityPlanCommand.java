package com.tomkasp.training.application.command;

import com.tomkasp.common.command.ResponseAwareDomainCommand;
import com.tomkasp.training.domain.TrainingIntensity;
import com.tomkasp.training.domain.TrainingSurveyId;

import java.time.DayOfWeek;

/**
 * @author Tomasz Kasprzycki
 */
public class AddTrainingIntensityPlanCommand extends ResponseAwareDomainCommand<Long> {

    private final DayOfWeek dayOfWeek;

    private final TrainingIntensity trainingIntensity;

    private final TrainingSurveyId trainingSurveyId;

    public AddTrainingIntensityPlanCommand(
        DayOfWeek dayOfWeek,
        TrainingIntensity trainingIntensity,
        TrainingSurveyId trainingSurveyId) {
        this.dayOfWeek = dayOfWeek;
        this.trainingIntensity = trainingIntensity;
        this.trainingSurveyId = trainingSurveyId;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TrainingIntensity getTrainingIntensity() {
        return trainingIntensity;
    }

    public TrainingSurveyId getTrainingSurveyId() {
        return trainingSurveyId;
    }
}
