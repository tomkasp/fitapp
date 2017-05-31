package com.tomkasp.common.common.domain.model;

import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
public class Weight implements Serializable, Comparable<Weight> {

    private final double value;
    private final Metric metric;

    public Weight(double value, Metric metric) {
        this.value = value;
        this.metric = (Metric) (metric == null ? Metrics.NEUTRAL : metric);
    }

    @Override
    public int compareTo(Weight o) {
        if (this.metric.equals(o.metric)) {
            return Double.compare(this.value, o.value);
        }else {
            throw new IllegalArgumentException("Cannot compare weight with different metrics");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight = (Weight) o;
        return Double.compare(weight.value, value) == 0 &&
            Objects.equals(metric, weight.metric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, metric);
    }
}
