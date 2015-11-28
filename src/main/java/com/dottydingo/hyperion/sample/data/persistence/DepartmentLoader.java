package com.dottydingo.hyperion.sample.data.persistence;

import com.dottydingo.hyperion.sample.data.model.PersistentDepartment;
import com.dottydingo.hyperion.sample.data.model.PersistentTerritory;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 */
@Component
public class DepartmentLoader extends AbstractLoader<PersistentDepartment,Long>
{
    private static final String REFERENCED_QUERY = "select count(*) from employees where department_id=:id";

    @Override
    protected Class<PersistentDepartment> getEntityClass()
    {
        return PersistentDepartment.class;
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
