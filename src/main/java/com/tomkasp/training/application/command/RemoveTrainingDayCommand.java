package com.tomkasp.training.application.command;

/**
 * @author Tomasz Kasprzycki
 */
public class RemoveTrainingDayCommand {

    private final Long trainingDayId;

    public RemoveTrainingDayCommand(Long trainingDayId) {
        this.trainingDayId = trainingDayId;
    }
}
