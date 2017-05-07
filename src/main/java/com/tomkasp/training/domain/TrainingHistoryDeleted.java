package com.tomkasp.training.domain;

import com.tomkasp.common.domain.model.DomainEvent;

import java.util.Date;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingHistoryDeleted implements DomainEvent {

    private final Long trainingHistoryId;

    public TrainingHistoryDeleted(Long trainingHistoryId) {
        this.trainingHistoryId = trainingHistoryId;
    }

    public Long getTrainingHistoryId() {
        return trainingHistoryId;
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
