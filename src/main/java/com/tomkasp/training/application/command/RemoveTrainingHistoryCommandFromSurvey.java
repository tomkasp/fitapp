package com.tomkasp.training.application.command;

/**
 * @author Tomasz Kasprzycki
 */
public class RemoveTrainingHistoryCommandFromSurvey {

    private final Long surveyId;

    private final Long trainingHistoryId;

    public RemoveTrainingHistoryCommandFromSurvey(Long surveyId, Long trainingHistoryId) {
        this.surveyId = surveyId;
        this.trainingHistoryId = trainingHistoryId;
    }

    public Long getTrainingHistoryId() {
        return trainingHistoryId;
    }

    public Long getSurveyId() {
        return surveyId;
    }
}
