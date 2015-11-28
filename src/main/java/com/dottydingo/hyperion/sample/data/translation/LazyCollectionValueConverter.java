package com.dottydingo.hyperion.sample.data.translation;

import com.dottydingo.hyperion.core.persistence.PersistenceContext;
import com.dottydingo.hyperion.core.translation.ValueConverter;

import java.util.Collection;

/**
 */
public class LazyCollectionValueConverter implements ValueConverter<Collection,Collection>
{
    @Override
    public Collection convertToClientValue(Collection persistentValue, PersistenceContext context)
    {
        if(persistentValue == null)
            return null;

        // touch the collection to retrieve it
        persistentValue.size();

        return persistentValue;
    }

    @Override
    public Collection convertToPersistentValue(Collection clientValue, PersistenceContext context)
    {
        return clientValue;
    }
}
