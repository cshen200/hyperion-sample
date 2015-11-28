package com.dottydingo.hyperion.sample.data.model;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 */
@Entity
@Table(name = "territories")
@DynamicUpdate
@AttributeOverrides(
        {@AttributeOverride(name = "id",column = @Column(name = "territory_id"))}
)
public class PersistentTerritory extends BaseSamplePersistentObject
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
