package com.dottydingo.hyperion.sample.data.translation;

import com.dottydingo.hyperion.core.translation.PropertyChangeEvaluator;

import java.util.Collection;

/**
 */
public class BagPropertyChangeEvaluator implements PropertyChangeEvaluator<Collection>
{
    @Override
    public boolean hasChanged(Collection oldValue, Collection newValue)
    {
        return !equals(oldValue,newValue);
    }

    private boolean equals(Collection object1, Collection object2)
    {
        if (object1 == object2)
        {
            return true;
        }
        if ((object1 == null) || (object2 == null))
        {
            return false;
        }
        return object1.size() == object2.size() && object1.containsAll(object2);
    }
}
