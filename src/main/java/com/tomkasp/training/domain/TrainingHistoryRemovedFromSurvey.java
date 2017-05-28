package com.tomkasp.training.domain;

import com.tomkasp.fitapp_common.common.domain.model.DomainEvent;

import java.util.Date;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingHistoryRemovedFromSurvey implements DomainEvent {

    private final Long trainingHistoryId;

    private final Long traingSurveyId;

    public TrainingHistoryRemovedFromSurvey(Long trainingHistoryId, Long traingSurveyId) {
        this.trainingHistoryId = trainingHistoryId;
        this.traingSurveyId = traingSurveyId;
    }

    public Long getTrainingHistoryId() {
        return trainingHistoryId;
    }

    public Long getTraingSurveyId() {
        return traingSurveyId;
    }

    @Override
    public int eventVersion() {
        return 0;
    }

    @Override
    public Date occurredOn() {
        return null;
    }
}
