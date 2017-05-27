package com.tomkasp.training.domain;

/**
 * @author Tomasz Kasprzycki
 */
public class TrainingGoal {

    private Double distance;
    private Double duration;
    private RunCategory runCategory;

    protected TrainingGoal() {
        super();
    }

    public TrainingGoal(Double distance, Double duration, RunCategory runCategory) {
        this.distance = distance;
        this.duration = duration;
        this.runCategory = runCategory;
    }
}
