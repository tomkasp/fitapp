package com.tomkasp.training.application.command;

import com.tomkasp.training.domain.TrainingIntensity;

import java.time.DayOfWeek;

/**
 * @author Tomasz Kasprzycki
 */
public class UpdateTrainingDayCommand {

    private final DayOfWeek dayOfWeek;

    private final TrainingIntensity trainingIntensity;

    private final Long trainingDayId;

    public UpdateTrainingDayCommand(DayOfWeek dayOfWeek, TrainingIntensity trainingIntensity, Long trainingDayId) {
        this.dayOfWeek = dayOfWeek;
        this.trainingIntensity = trainingIntensity;
        this.trainingDayId = trainingDayId;
    }
}
