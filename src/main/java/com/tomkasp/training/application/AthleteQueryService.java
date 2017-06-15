package com.tomkasp.training.application;

import com.tomkasp.training.domain.trainingplan.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class AthleteQueryService {

    private final TrainingRepository trainingRepository;

    @Autowired
    public AthleteQueryService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public void getAthleteTrainingPlan() {

    }

}
