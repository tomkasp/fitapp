package com.tomkasp.fitapp.activitytrackers.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.tomkasp.fitapp_common.domain.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "trackers_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class TrackersData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "activity_source")
    @Enumerated(EnumType.STRING)
    ActivitySource activitySource;

//    @Type(type = "json")
//    @Column(name = "tracker_metadata", columnDefinition = "json")
//    TrackersMetadata trackerMetadata;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    @Fetch(FetchMode.JOIN)
    User user;

    public TrackersData activitySource(final ActivitySource activitySource) {
        this.activitySource = activitySource;
        return this;
    }

//    public TrackersData trackerMetadata(final TrackersMetadata trackerMetadata) {
//        this.trackerMetadata = trackerMetadata;
//        return this;
//    }

    public TrackersData user(final User user) {
        this.user = user;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TrackersData setId(Long id) {
        this.id = id;
        return this;
    }

    public ActivitySource getActivitySource() {
        return activitySource;
    }

    public TrackersData setActivitySource(ActivitySource activitySource) {
        this.activitySource = activitySource;
        return this;
    }

//    public TrackersMetadata getTrackerMetadata() {
//        return trackerMetadata;
//    }

//    public TrackersData setTrackerMetadata(TrackersMetadata trackerMetadata) {
//        this.trackerMetadata = trackerMetadata;
//        return this;
//    }

    public User getUser() {
        return user;
    }

    public TrackersData setUser(User user) {
        this.user = user;
        return this;
    }
}
