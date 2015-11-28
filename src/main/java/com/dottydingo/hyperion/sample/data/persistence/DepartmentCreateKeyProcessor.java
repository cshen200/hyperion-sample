package com.dottydingo.hyperion.sample.data.persistence;

import com.dottydingo.hyperion.core.persistence.CreateKeyProcessor;
import com.dottydingo.hyperion.core.persistence.PersistenceContext;
import com.dottydingo.hyperion.sample.data.api.Department;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Allow create or update calls on Department based on the name alternate key.
 */
public class DepartmentCreateKeyProcessor implements CreateKeyProcessor<Department, Long>
{
    @Autowired
    private DepartmentLoader departmentLoader;

    @Override
    public Long lookup(Department item, PersistenceContext persistenceContext)
    {
        if(item.getName() == null)
            return null;

        return departmentLoader.findByField("name",item.getName());
    }
}
