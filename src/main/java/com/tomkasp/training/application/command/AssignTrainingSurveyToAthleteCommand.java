package com.tomkasp.training.application.command;

import com.tomkasp.common.command.ResponseAwareDomainCommand;
import com.tomkasp.common.domain.model.Height;
import com.tomkasp.common.domain.model.Weight;
import com.tomkasp.training.domain.*;
import org.joda.time.LocalDate;
import org.springframework.data.geo.Distance;

import java.time.Duration;


/**
 * @author Tomasz Kasprzycki
 */
public class AssignTrainingSurveyToAthleteCommand extends ResponseAwareDomainCommand<Long> {

    private final BaseInformation baseInformation;
    private final HealthInformation healthInformation;
    private final NutritionInformation nutritionInformation;
    private final TrainingGoal trainingGoal;

    public AssignTrainingSurveyToAthleteCommand(LocalDate birthday,
                                                Weight weight,
                                                Height height,
                                                boolean healthContraindications,
                                                boolean stressTest,
                                                boolean bloodTest,
                                                Duration hoursOfSleep,
                                                Duration duration,
                                                Distance distance,
                                                RunCategory runCategory,
                                                boolean meatAcceptance,
                                                boolean dairiesAcceptance,
                                                boolean allergies,
                                                boolean foodIntolerance) {
        this.healthInformation = new HealthInformation(
            healthContraindications,
            stressTest,
            bloodTest,
            hoursOfSleep);
        this.baseInformation = new BaseInformation(birthday,
            weight,
            height);
        nutritionInformation = new NutritionInformation(meatAcceptance,
            dairiesAcceptance,
            allergies,
            foodIntolerance);
        trainingGoal = new TrainingGoal(distance,
            duration,
            runCategory);
    }

    public BaseInformation getBaseInformation() {
        return this.baseInformation;
    }

    public HealthInformation getHealthInformation() {
        return healthInformation;
    }

    public NutritionInformation getNutritionInformation() {
        return nutritionInformation;
    }

    public TrainingGoal getTrainingGoal() {
        return trainingGoal;
    }
}
