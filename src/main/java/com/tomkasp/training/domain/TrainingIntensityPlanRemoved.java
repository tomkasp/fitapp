package com.tomkasp.training.domain;

import com.tomkasp.common.common.domain.model.DomainEvent;

import java.util.Date;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingIntensityPlanRemoved implements DomainEvent {

    private final Long trainingIntensityPlanId;

    public TrainingIntensityPlanRemoved(Long trainingIntensityPlanId) {
        this.trainingIntensityPlanId = trainingIntensityPlanId;
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
