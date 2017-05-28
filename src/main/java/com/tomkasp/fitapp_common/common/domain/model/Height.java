package com.tomkasp.fitapp_common.common.domain.model;

import org.springframework.data.geo.Metric;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
public class Height implements Serializable, Comparable<Height> {

    private final double value;
    private final Metric metric;

    public Height(double value, HeightMetrics metric) {
        this.value = value;
        this.metric = (Metric) (metric == null ? HeightMetrics.NEUTRAL : metric);
    }

    @Override
    public int compareTo(Height o) {
        if (this.metric.equals(o.metric)) {
            return Double.compare(this.value, o.value);
        } else {
            throw new IllegalArgumentException("Cannot compare height with different metrics");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Height height = (Height) o;
        return Double.compare(height.value, value) == 0 &&
            Objects.equals(metric, height.metric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, metric);
    }
}
