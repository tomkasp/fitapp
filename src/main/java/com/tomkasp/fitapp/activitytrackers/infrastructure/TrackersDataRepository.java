package com.tomkasp.fitapp.activitytrackers.infrastructure;

import com.tomkasp.fitapp.activitytrackers.domain.TrackersData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackersDataRepository extends JpaRepository<TrackersData, Long> {

    Optional<TrackersData> findByUserId(Long id);



}
