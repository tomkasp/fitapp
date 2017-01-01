package com.tomkasp.fitapp.activitytrackers.domain;

import com.tomkasp.fitapp.sharedkernel.jsontype.JsonStringType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.MappedSuperclass;

/**
 * @author Tomasz Kasprzycki
 */
@TypeDefs({
    @TypeDef(name = "json", typeClass = JsonStringType.class),
})
@MappedSuperclass
public class BaseEntity { }
