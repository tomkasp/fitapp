package com.tomkasp.fitapp.activitytrackers.infrastructure;

import com.tomkasp.fitapp.activitytrackers.domain.TrackersMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackersMetadataRepository extends JpaRepository<TrackersMetadata, Long> {
}
