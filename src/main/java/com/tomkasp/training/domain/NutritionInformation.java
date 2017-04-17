package com.tomkasp.training.domain;

import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
public class NutritionInformation {

    boolean meatAcceptance;
    boolean dairyedAcceptance;
    boolean allergies;
    boolean foodIntolerance;

    protected NutritionInformation() {
        super();
    }

    public NutritionInformation(boolean meatAcceptance, boolean dairyedAcceptance, boolean allergies, boolean foodIntolerance) {
        this.meatAcceptance = meatAcceptance;
        this.dairyedAcceptance = dairyedAcceptance;
        this.allergies = allergies;
        this.foodIntolerance = foodIntolerance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutritionInformation that = (NutritionInformation) o;
        return meatAcceptance == that.meatAcceptance &&
            dairyedAcceptance == that.dairyedAcceptance &&
            allergies == that.allergies &&
            foodIntolerance == that.foodIntolerance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meatAcceptance, dairyedAcceptance, allergies, foodIntolerance);
    }
}
