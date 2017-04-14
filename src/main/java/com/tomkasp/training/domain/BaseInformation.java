package com.tomkasp.training.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class BaseInformation {

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    private String weight;

    @Column(nullable = false)
    private String height;

    public BaseInformation(){
        super();
    }

    public BaseInformation(Date birthday, String weight, String height) {
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }
}
