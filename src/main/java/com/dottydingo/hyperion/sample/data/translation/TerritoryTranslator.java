package com.dottydingo.hyperion.sample.data.translation;

import com.dottydingo.hyperion.core.translation.DefaultAuditingTranslator;
import com.dottydingo.hyperion.sample.data.api.Territory;
import com.dottydingo.hyperion.sample.data.model.PersistentTerritory;

/**
 */
public class TerritoryTranslator extends DefaultAuditingTranslator<Territory,PersistentTerritory>
{
    public TerritoryTranslator()
    {
        super(Territory.class, PersistentTerritory.class);
    }
}
