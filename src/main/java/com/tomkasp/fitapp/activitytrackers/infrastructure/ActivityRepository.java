package com.tomkasp.fitapp.activitytrackers.infrastructure;

import com.tomkasp.fitapp.activitytrackers.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
public interface ActivityRepository extends JpaRepository<Activity, Long> {


    Optional<List<Activity>> findByUserIdOrderByDateTimeAsc(Long id);

}

