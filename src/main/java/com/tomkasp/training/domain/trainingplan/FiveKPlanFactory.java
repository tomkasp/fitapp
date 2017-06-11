package com.tomkasp.training.domain.trainingplan;

import com.tomkasp.training.domain.RunTempos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public class FiveKPlanFactory {

    public static List<String> calculate(RunTempos runTempos) {
        List<String> trainingDays = new ArrayList<>();
        String firstDay = "free";
        String secondDay = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        String thirdDay = "free";
        String fourthDay = "10min run with tempo " + runTempos.easy() +
            "(3min " + runTempos.maximumOxygen() + ")";
        String fifthDay = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        String sixthDay = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        String seventhDAy = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        String eightDay = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        String ninthDat = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        String tenthDay = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        String eleventhDay = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        String twelveDay = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        String thirteenDay = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        String fourtennDay = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();
        String fifteenthDay = "15min run with tempo" + runTempos.easy() + "15min run with tempo:" + runTempos.longRunMax();

        trainingDays.add(firstDay);
        trainingDays.add(secondDay);
        trainingDays.add(thirdDay);
        trainingDays.add(fourthDay);
        trainingDays.add(fifthDay);
        trainingDays.add(sixthDay);
        trainingDays.add(seventhDAy);
        trainingDays.add(eightDay);
        trainingDays.add(ninthDat);
        trainingDays.add(tenthDay);
        trainingDays.add(eleventhDay);
        return trainingDays;
    }
}
