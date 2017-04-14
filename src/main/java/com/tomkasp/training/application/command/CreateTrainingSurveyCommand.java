package com.tomkasp.training.application.command;

import com.tomkasp.training.domain.BaseInformation;

import java.util.Date;


/**
 * @author Tomasz Kasprzycki
 */
public class CreateTrainingSurveyCommand {

    private final BaseInformation baseInformation;

    public CreateTrainingSurveyCommand(Date birthday,
                                       String weight,
                                       String height) {
        this.baseInformation = new BaseInformation(birthday, weight, height);
    }

    public BaseInformation getBaseInformation() {
        return this.baseInformation;
    }
}
