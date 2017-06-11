package com.tomkasp.training.domain.trainingplan;

import com.tomkasp.training.domain.RunTempos;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrainingPlan {

    List<String> fetch(RunTempos runTempos);

}

