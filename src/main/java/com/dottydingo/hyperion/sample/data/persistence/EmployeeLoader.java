package com.dottydingo.hyperion.sample.data.persistence;

import com.dottydingo.hyperion.sample.data.model.PersistentEmployee;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class EmployeeLoader extends AbstractLoader<PersistentEmployee,Long>
{

    @Override
    protected Class<PersistentEmployee> getEntityClass()
    {
        return PersistentEmployee.class;
    }

    @Override
    protected Class<Long> getIdClass()
    {
        return Long.class;
    }
}
