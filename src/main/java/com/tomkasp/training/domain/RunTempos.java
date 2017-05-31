package com.tomkasp.training.domain;

import com.tomkasp.common.domain.ValueObject;

/**
 * @author Tomasz Kasprzycki
 */
public class RunTempos extends ValueObject {

    private long easy;
    private long maximumOxygen;
    private long longRunMax;
    private long longRunMin;
    private long yasso800;


    public RunTempos(long easy, long maximumOxygen, long longRunMax, long longRunMin, long yasso800) {
        this.easy = easy;
        this.maximumOxygen = maximumOxygen;
        this.longRunMax = longRunMax;
        this.longRunMin = longRunMin;
        this.yasso800 = yasso800;
    }

    public long easy() {
        return easy;
    }

    public long longRunMax() {
        return longRunMax;
    }

    public long longrunMin() {
        return longRunMin;
    }

    public long yasso800() {
        return yasso800;
    }

    public long maximumOxygen() {
        return maximumOxygen;
    }
}
