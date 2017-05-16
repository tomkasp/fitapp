package com.tomkasp.training.domain;

import com.tomkasp.common.domain.model.DomainEvent;

import java.util.Date;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingIntensityPlanUpdated implements DomainEvent {

    @Override
    public int eventVersion() {
        return 0;
    }

    @Override
    public Date occurredOn() {
        return null;
    }
}
