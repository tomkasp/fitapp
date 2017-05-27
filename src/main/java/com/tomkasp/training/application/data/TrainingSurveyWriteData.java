package com.tomkasp.training.application.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.tomkasp.training.domain.MeasureType;
import com.tomkasp.training.domain.RunCategory;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author Tomasz Kasprzycki
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class TrainingSurveyWriteData {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthday;
    Double weight;
    Double height;
    boolean healthContraindications;
    boolean stressTest;
    boolean bloodTest;
    Double hoursOfSleep;
    Double duration;
    Double distance;
    RunCategory runCategory;
    boolean meatAcceptance;
    boolean dairiesAcceptance;
    boolean allergies;
    boolean foodIntolerance;
    MeasureType measureType;


    public LocalDate getBirthday() {
        return birthday;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    public boolean isHealthContraindications() {
        return healthContraindications;
    }

    public boolean isStressTest() {
        return stressTest;
    }

    public boolean isBloodTest() {
        return bloodTest;
    }

    public Double getHoursOfSleep() {
        return hoursOfSleep;
    }

    public Double getDuration() {
        return duration;
    }

    public Double getDistance() {
        return distance;
    }

    public RunCategory getRunCategory() {
        return runCategory;
    }

    public boolean isMeatAcceptance() {
        return meatAcceptance;
    }

    public boolean isDairiesAcceptance() {
        return dairiesAcceptance;
    }

    public boolean isAllergies() {
        return allergies;
    }

    public boolean isFoodIntolerance() {
        return foodIntolerance;
    }

    public MeasureType getMeasureType() {
        return measureType;
    }

    @Override
    public String toString() {
        return "TrainingSurveyWriteData{" +
            "birthday=" + birthday +
            ", weight=" + weight +
            ", height=" + height +
            ", healthContraindications=" + healthContraindications +
            ", stressTest=" + stressTest +
            ", bloodTest=" + bloodTest +
            ", hoursOfSleep=" + hoursOfSleep +
            ", duration=" + duration +
            ", distance=" + distance +
            ", runCategory=" + runCategory +
            ", meatAcceptance=" + meatAcceptance +
            ", dairiesAcceptance=" + dairiesAcceptance +
            ", allergies=" + allergies +
            ", foodIntolerance=" + foodIntolerance +
            ", measureType=" + measureType +
            '}';
    }
}
