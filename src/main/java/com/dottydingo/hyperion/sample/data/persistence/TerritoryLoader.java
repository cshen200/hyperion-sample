package com.dottydingo.hyperion.sample.data.persistence;

import com.dottydingo.hyperion.sample.data.model.PersistentTerritory;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class TerritoryLoader extends AbstractLoader<PersistentTerritory,Long>
{
    private static final String REFERENCED_QUERY = "select count(*) from employee_territories where territory_id=:id";

    @Override
    protected Class<PersistentTerritory> getEntityClass()
    {
        return PersistentTerritory.class;
    }

    @Override
    protected Class<Long> getIdClass()
    {
        return Long.class;
    }

    public boolean isReferenced(Long id)
    {
        return isReferenced(REFERENCED_QUERY,id);
    }
}
