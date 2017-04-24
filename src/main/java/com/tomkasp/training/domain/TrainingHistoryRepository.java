package com.tomkasp.training.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrainingHistoryRepository extends JpaRepository<TrainingHistory, Long> {
}
