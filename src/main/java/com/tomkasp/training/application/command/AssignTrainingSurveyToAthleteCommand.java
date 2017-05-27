package com.tomkasp.training.application.command;

import com.tomkasp.common.command.ResponseAwareDomainCommand;
import com.tomkasp.training.domain.*;
import org.joda.time.LocalDate;


/**
 * @author Tomasz Kasprzycki
 */
public class AssignTrainingSurveyToAthleteCommand extends ResponseAwareDomainCommand<Long> {

    private final BaseInformation baseInformation;
    private final HealthInformation healthInformation;
    private final NutritionInformation nutritionInformation;
    private final TrainingGoal trainingGoal;
    private final MeasureType measureType;

    public AssignTrainingSurveyToAthleteCommand(LocalDate birthday,
                                                Double weight,
                                                Double height,
                                                boolean healthContraindications,
                                                boolean stressTest,
                                                boolean bloodTest,
                                                Double hoursOfSleep,
                                                Double durationRunGoal,
                                                Double distanceGoal,
                                                RunCategory runCategoryGoal,
                                                boolean meatAcceptance,
                                                boolean dairiesAcceptance,
                                                boolean allergies,
                                                boolean foodIntolerance,
                                                MeasureType measureType) {
        this.measureType = measureType;
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
        trainingGoal = new TrainingGoal(distanceGoal,
            durationRunGoal,
            runCategoryGoal);
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

    public MeasureType getMeasureType() {
        return measureType;
    }
}
