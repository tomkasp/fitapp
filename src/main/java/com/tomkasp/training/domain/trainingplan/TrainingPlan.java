package com.tomkasp.training.domain.trainingplan;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrainingPlan {

    List<String> fetch(RunTempos runTempos);

}

