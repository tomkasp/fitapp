package com.tomkasp.fitapp.activitytrackers.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by tomkasp on 11/12/16.
 */

@Configuration
@EnableJpaRepositories("com.tomkasp.fitapp.activitytrackers.infrastructure")
public class ActivityTrackersConfiguration {
}
