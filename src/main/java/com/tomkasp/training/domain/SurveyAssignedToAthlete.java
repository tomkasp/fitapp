package com.tomkasp.training.domain;

import com.tomkasp.common.domain.model.DomainEvent;

import java.util.Date;

/**
 * @author Tomasz Kasprzycki
 */
public class SurveyAssignedToAthlete implements DomainEvent {

    private final Long athleteId;
    private final Long surveyId;
    private final BaseInformation baseInformation;

    public SurveyAssignedToAthlete(Long athleteId, Long surveyId, BaseInformation baseInformation) {
        this.athleteId = athleteId;
        this.surveyId = surveyId;
        this.baseInformation = baseInformation;
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
