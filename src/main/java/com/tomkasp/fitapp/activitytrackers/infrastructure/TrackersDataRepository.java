package com.tomkasp.fitapp.activitytrackers.infrastructure;

import com.tomkasp.fitapp.activitytrackers.domain.TrackersData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackersDataRepository extends JpaRepository<TrackersData, Long> {
}
