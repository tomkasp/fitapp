package com.tomkasp.training.application;

import com.tomkasp.common.service.UserService;
import com.tomkasp.training.application.command.CalculateAthleteTrainingCommand;
import com.tomkasp.training.domain.Athlete;
import com.tomkasp.training.domain.AthleteRepository;
import com.tomkasp.training.domain.trainingplan.Training;
import com.tomkasp.training.domain.trainingplan.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class AthleteApplicationService {

    private final AthleteRepository athleteRepository;
    private final TrainingRepository trainingRepository;
    private final UserService userService;


    @Autowired
    public AthleteApplicationService(AthleteRepository athleteRepository, TrainingRepository trainingRepository, UserService userService) {
        this.athleteRepository = athleteRepository;
        this.trainingRepository = trainingRepository;
        this.userService = userService;
    }

    public void calculateTraining(CalculateAthleteTrainingCommand calculateAthleteTrainingCommand) {
        final Athlete athlete = athleteData();
        final Training training = athlete.assignTrainingToAthlete(
            calculateAthleteTrainingCommand.getTrainingRunCategory(),
            calculateAthleteTrainingCommand.getLastRaceResult()
        );
        trainingRepository.save(training);
        calculateAthleteTrainingCommand.setResponse(training.getId());
    }

    private Athlete athleteData() {
        final Optional<Athlete> athlete = athleteRepository.findByUserId(userService.getUserWithAuthorities().getId());
        return athlete.get();
    }

}
