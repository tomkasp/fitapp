package com.tomkasp.training.domain.trainingplan;

import com.tomkasp.training.domain.RunTempos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public class FiveKPlanFactory {


    public void calculate(RunTempos runTempos) {
        List<String> trainingDays = new ArrayList<>();
        String firstDay = "free";
        String secondDay = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        trainingDays.add(firstDay);
        trainingDays.add(secondDay);

    }

}
