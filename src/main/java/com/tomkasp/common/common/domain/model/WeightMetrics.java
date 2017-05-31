package com.tomkasp.common.common.domain.model;

import org.springframework.data.geo.Metric;

/**
 * @author Tomasz Kasprzycki
 */
public enum WeightMetrics implements Metric {

    KILOGRAM(0.453592, "kg"),
    POUND(2.2046, "lbs"),
    NEUTRAL(1, "");

    private final double multiplier;
    private final String abbreviation;

    private WeightMetrics(double multiplier, String abbreviation) {
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
