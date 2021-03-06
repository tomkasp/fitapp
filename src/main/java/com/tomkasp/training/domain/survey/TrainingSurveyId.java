package com.tomkasp.training.domain.survey;

import com.tomkasp.common.common.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class TrainingSurveyId extends ValueObject {

    @Column
    private Long trainingSurveyId;

    protected TrainingSurveyId(){
        super();
    }

    public TrainingSurveyId(Long trainingSurveyId) {
        this.trainingSurveyId = trainingSurveyId;
    }

    public Long getTrainingSurveyId() {
        return trainingSurveyId;
    }
}
