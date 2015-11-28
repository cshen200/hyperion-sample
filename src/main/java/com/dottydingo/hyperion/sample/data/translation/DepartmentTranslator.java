package com.dottydingo.hyperion.sample.data.translation;

import com.dottydingo.hyperion.core.translation.DefaultAuditingTranslator;
import com.dottydingo.hyperion.sample.data.api.Department;
import com.dottydingo.hyperion.sample.data.model.PersistentDepartment;

/**
 */
public class DepartmentTranslator extends DefaultAuditingTranslator<Department,PersistentDepartment>
{
    public DepartmentTranslator()
    {
        super(Department.class, PersistentDepartment.class);
    }
}
