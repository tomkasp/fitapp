package com.tomkasp.training.domain;

import com.tomkasp.common.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class TrainingSurveyId extends ValueObject {

    @Column
    private Long traininSurveyId;

    protected TrainingSurveyId(){
        super();
    }

    public TrainingSurveyId(Long traininSurveyId) {
        this.traininSurveyId = traininSurveyId;
    }

    public Long getTraininSurveyId() {
        return traininSurveyId;
    }
}
