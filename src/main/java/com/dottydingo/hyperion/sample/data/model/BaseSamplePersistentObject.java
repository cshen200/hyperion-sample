package com.dottydingo.hyperion.sample.data.model;

import com.dottydingo.hyperion.jpa.model.BaseAuditablePersistentObject;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 */
@MappedSuperclass
public class BaseSamplePersistentObject extends BaseAuditablePersistentObject<Long>
{
    @Id()
    @GeneratedValue
    private Long id;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
