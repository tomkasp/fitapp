package com.tomkasp.fitapp.activitytrackers.domain;

import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki
 */
public class TrackersMetadata implements Serializable {

    String email;
    String stravaCode;

    public String getEmail() {
        return email;
    }

    public TrackersMetadata setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getStravaCode() {
        return stravaCode;
    }

    public TrackersMetadata setStravaCode(String stravaCode) {
        this.stravaCode = stravaCode;
        return this;
    }

    public TrackersMetadata email(final String email) {
        this.email = email;
        return this;
    }


}
