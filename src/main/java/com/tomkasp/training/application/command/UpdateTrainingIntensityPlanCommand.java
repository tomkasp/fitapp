package com.tomkasp.training.application.command;

import com.tomkasp.training.domain.TrainingIntensity;

import java.time.DayOfWeek;

/**
 * @author Tomasz Kasprzycki
 */
public class UpdateTrainingIntensityPlanCommand {

    private final Long trainingIntensityPlanId;

    private final DayOfWeek dayOfWeek;

    private final TrainingIntensity trainingIntensity;

    public UpdateTrainingIntensityPlanCommand(
        Long trainingIntensityPlanId,
        DayOfWeek dayOfWeek,
        TrainingIntensity trainingIntensity) {

        this.dayOfWeek = dayOfWeek;
        this.trainingIntensity = trainingIntensity;
        this.trainingIntensityPlanId = trainingIntensityPlanId;
    }

    public Long getTrainingIntensityPlanId() {
        return trainingIntensityPlanId;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TrainingIntensity getTrainingIntensity() {
        return trainingIntensity;
    }
}
