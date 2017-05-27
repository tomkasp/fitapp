package com.tomkasp.training.domain;

/**
 * @author Tomasz Kasprzycki
 */
public class MeasureSystem {

    MeasureType measureType;

    public MeasureSystem() {
        super();
    }

    public MeasureSystem(MeasureType measureType) {
        this.measureType = measureType;
    }

    public String getMassUnit() {
        if (measureType.equals(MeasureType.Metric)) {
            return "kg";
        } else {
            return "pound";
        }
    }

    public String getHeightUnit() {
        if (measureType.equals(MeasureType.Metric)) {
            return "cm";
        } else {
            return "foot";
        }
    }
}
