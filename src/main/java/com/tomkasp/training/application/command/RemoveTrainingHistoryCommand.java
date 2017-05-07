package com.tomkasp.training.application.command;

/**
 * @author Tomasz Kasprzycki
 */
public class RemoveTrainingHistoryCommand {

    private final Long trainingHistoryId;

    public RemoveTrainingHistoryCommand(Long trainingHistoryId) {
        this.trainingHistoryId = trainingHistoryId;
    }

    public Long getTrainingHistoryId() {
        return trainingHistoryId;
    }
}
