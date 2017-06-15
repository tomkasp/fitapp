package com.tomkasp.training.application.data;

import com.tomkasp.training.domain.survey.TrainingSurvey;
import org.mapstruct.Mapper;

/**
 * @author Tomasz Kasprzycki
 */
@Mapper(componentModel = "spring", uses = {})
public interface TrainingSurveyMapper {

    TrainingSurveyReadData trainingSurveyToTrainingSurveyData(TrainingSurvey trainingSurvey);

}
