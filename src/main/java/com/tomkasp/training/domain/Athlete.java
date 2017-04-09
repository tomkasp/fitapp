package com.tomkasp.training.domain;

import com.tomkasp.domain.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "athlete")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Athlete {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "USER_ID", unique = true, nullable = false, updatable = false)
    private User user;

    public TrainingSurvey assignSurvey(BaseInformation baseInformation) {
        TrainingSurvey trainingSurvey = new TrainingSurvey(this, baseInformation);
        return trainingSurvey;
    }


    public Long getId() {
        return id;
    }

    public Athlete setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Athlete setName(String name) {
        this.name = name;
        return this;
    }
}
