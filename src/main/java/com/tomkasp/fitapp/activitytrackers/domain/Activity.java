package com.tomkasp.fitapp.activitytrackers.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.tomkasp.domain.User;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "activity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "activity_source")
    @Enumerated(EnumType.STRING)
    private ActivitySource activitySource;

    @Column(name = "integration_id")
    private String integrationId;

    @Column(name = "activity_type")
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    @Column(name = "activity_average_speed")
    private String activityAverageSpeed;

    @Column(name = "activity_duration")
    private String activityDuration;

    @Column(name = "activity_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    @Fetch(FetchMode.JOIN)
    User user;

    public Long getId() {
        return id;
    }

    public ActivitySource getActivitySource() {
        return activitySource;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public String getActivityAverageSpeed() {
        return activityAverageSpeed;
    }

    public String getActivityDuration() {
        return activityDuration;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
