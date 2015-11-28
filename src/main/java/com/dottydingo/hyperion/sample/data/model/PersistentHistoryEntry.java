package com.dottydingo.hyperion.sample.data.model;

import com.dottydingo.hyperion.api.HistoryAction;
import com.dottydingo.hyperion.jpa.model.BasePersistentHistoryEntry;

import javax.persistence.*;
import java.util.Date;

/**
 */
@Entity
@Table(name = "entity_history")
@Access(AccessType.PROPERTY)
public class PersistentHistoryEntry extends BasePersistentHistoryEntry<Long>
{
    @Id()
    @GeneratedValue
    @Column(name = "entity_history_id")
    @Override
    public Long getId()
    {
        return super.getId();
    }

    @Column(name = "entity_type")
    @Override
    public String getEntityType()
    {
        return super.getEntityType();
    }

    @Column(name = "entity_id")
    @Override
    public Long getEntityId()
    {
        return super.getEntityId();
    }

    @Column(name = "history_action")
    @Enumerated(EnumType.STRING)
    @Override
    public HistoryAction getHistoryAction()
    {
        return super.getHistoryAction();
    }

    @Column(name = "api_version")
    @Override
    public Integer getApiVersion()
    {
        return super.getApiVersion();
    }

    @Column(name = "serialized_entry")
    @Lob
    @Override
    public String getSerializedEntry()
    {
        return super.getSerializedEntry();
    }

    @Column(name = "user")
    @Override
    public String getUser()
    {
        return super.getUser();
    }

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @Override
    public Date getTimestamp()
    {
        return super.getTimestamp();
    }
}
