package com.tomkasp.training.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class BaseInformation {

    @Column(length = 100, unique = true, nullable = false)
    private String birthdate;

    public String getBirthdate() {
        return birthdate;
    }

}
