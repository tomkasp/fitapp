package com.tomkasp.training.domain.trainingplan;

import com.tomkasp.training.domain.RunCategory;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingPlanFactory {


    public static List<String> build(RunCategory runCategory, RunTempos runTempos) {
        if (runCategory.equals(RunCategory.FIVE_K)) {
            return FiveKPlanFactory.calculate(runTempos);
        }
        return null;
    }

}
