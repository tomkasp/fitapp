package com.tomkasp.common.domain.model;

import org.springframework.data.geo.Metric;

/**
 * @author Tomasz Kasprzycki
 */
public enum HeightMetrics implements Metric {

    CENTIMETER(30.48, "kg"),
    FOOT(0.0328, "lbs"),
    NEUTRAL(1, "");

    private final double multiplier;
    private final String abbreviation;

    private HeightMetrics(double multiplier, String abbreviation) {
        this.multiplier = multiplier;
        this.abbreviation = abbreviation;
    }

    public double getMultiplier() {
        return this.multiplier;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }
}
