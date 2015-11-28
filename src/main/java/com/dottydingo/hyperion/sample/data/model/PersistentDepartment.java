package com.dottydingo.hyperion.sample.data.model;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 */
@Entity
@Table(name = "departments")
@DynamicUpdate
@AttributeOverrides(
        {@AttributeOverride(name = "id",column = @Column(name = "department_id"))}
)
public class PersistentDepartment extends BaseSamplePersistentObject
{
    @Column(name = "name", unique = true,length = 64)
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
