package com.tomkasp.training.application.command;

/**
 * @author Tomasz Kasprzycki
 */
public class RemoveTrainingIntensityPlanCommand {

    private final Long trainingDayId;

    public RemoveTrainingIntensityPlanCommand(Long trainingDayId) {
        this.trainingDayId = trainingDayId;
    }

    public Long getTrainingDayId() {
        return trainingDayId;
    }
}
