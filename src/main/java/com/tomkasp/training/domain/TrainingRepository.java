package com.tomkasp.training.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {
}
