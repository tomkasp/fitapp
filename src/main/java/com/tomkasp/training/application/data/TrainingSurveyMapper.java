package com.tomkasp.training.application.data;

import com.tomkasp.training.domain.TrainingSurvey;
import org.mapstruct.Mapper;

/**
 * @author Tomasz Kasprzycki
 */
@Mapper(componentModel = "spring", uses = {})
public interface TrainingSurveyMapper {

    TrainingSurveyData trainingSurveyToTrainingSurveyData(TrainingSurvey trainingSurvey);

}
