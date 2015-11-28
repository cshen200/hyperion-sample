package com.dottydingo.hyperion.sample.data.persistence;

import com.dottydingo.hyperion.core.persistence.CreateKeyProcessor;
import com.dottydingo.hyperion.core.persistence.PersistenceContext;
import com.dottydingo.hyperion.sample.data.api.Territory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Allow create or update calls on Territory based on the name alternate key.
 */
public class TerritoryCreateKeyProcessor implements CreateKeyProcessor<Territory, Long>
{
    @Autowired
    private TerritoryLoader territoryLoader;

    @Override
    public Long lookup(Territory item, PersistenceContext persistenceContext)
    {
        if(item.getName() == null)
            return null;

        return territoryLoader.findByField("name",item.getName());
    }
}
