package com.dottydingo.hyperion.sample.data.api;

import com.dottydingo.hyperion.api.BaseAuditableApiObject;
import com.dottydingo.hyperion.api.Endpoint;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 */
@JsonPropertyOrder({"id","name","created","createdBy","modified","modifiedBy"})
@Endpoint("Territory")
public class Territory extends BaseAuditableApiObject<Long>
{
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
