package com.tomkasp.training.domain;

import com.tomkasp.common.domain.model.Height;
import com.tomkasp.common.domain.model.Weight;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class BaseInformation {

    @Column(nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate birthday;

    @Column(nullable = false)
    private Weight weight;

    @Column(nullable = false)
    private Height height;

    protected BaseInformation(){
        super();
    }

    public BaseInformation(LocalDate birthday, Weight weight, Height height) {
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Weight getWeight() {
        return weight;
    }

    public Height getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseInformation that = (BaseInformation) o;
        return Objects.equals(birthday, that.birthday) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(height, that.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthday, weight, height);
    }
}
