package com.dottydingo.hyperion.sample.data.translation;

import com.dottydingo.hyperion.core.translation.PropertyChangeEvaluator;

import java.util.Date;

/**
 */
public class DatePropertyChangeEvaluator implements PropertyChangeEvaluator<Date>
{
    @Override
    public boolean hasChanged(Date oldValue, Date newValue)
    {
        return !equals(oldValue,newValue);
    }



    private boolean equals(Date object1, Date object2)
    {
        if (object1 == object2)
        {
            return true;
        }
        if ((object1 == null) || (object2 == null))
        {
            return false;
        }
        return object1.getTime() == object2.getTime();
    }
}
