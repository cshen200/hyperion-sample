package com.dottydingo.hyperion.sample.data.validation;

import com.dottydingo.hyperion.core.persistence.PersistenceContext;
import com.dottydingo.hyperion.core.validation.ValidationErrorContext;
import com.dottydingo.hyperion.sample.data.api.Department;
import com.dottydingo.hyperion.sample.data.model.PersistentDepartment;
import com.dottydingo.hyperion.sample.data.persistence.DepartmentLoader;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Validate Departments
 */
public class DepartmentValidator extends BaseServiceValidator<Department,PersistentDepartment>
{
    @Autowired
    private DepartmentLoader departmentLoader;

    // for testing
    protected void setDepartmentLoader(DepartmentLoader departmentLoader)
    {
        this.departmentLoader = departmentLoader;
    }

    @Override
    protected void validateCreateConflict(Department clientObject, ValidationErrorContext errorContext, PersistenceContext persistenceContext)
    {
        if(clientObject.getName() != null && !departmentLoader.isUnique("name",clientObject.getName()))
            errorContext.addValidationError(VALIDATION_VALUE_NOT_UNIQUE,"name","Department","name",clientObject.getName());
    }

    @Override
    protected void validateCreate(Department clientObject, ValidationErrorContext errorContext, PersistenceContext persistenceContext)
    {
        validateRequired(errorContext,"name",clientObject.getName());
        validateLength(errorContext,"name",clientObject.getName(),64);
    }

    @Override
    protected void validateUpdateConflict(Department clientObject, PersistentDepartment persistentObject,
                                          ValidationErrorContext errorContext, PersistenceContext persistenceContext)
    {
        if(valueChanged(clientObject.getName(),persistentObject.getName())
                && !departmentLoader.isUnique("name",clientObject.getName()))
            errorContext.addValidationError(VALIDATION_VALUE_NOT_UNIQUE,"name","Department","name",clientObject.getName());
    }

    @Override
    protected void validateUpdate(Department clientObject, PersistentDepartment persistentObject,
                                  ValidationErrorContext errorContext, PersistenceContext persistenceContext)
    {
        validateLength(errorContext,"name",clientObject.getName(),64);
    }

    @Override
    protected void validateDeleteConflict(PersistentDepartment persistentObject, ValidationErrorContext errorContext,
                                          PersistenceContext persistenceContext)
    {
        if(departmentLoader.isReferenced(persistentObject.getId()))
            errorContext.addValidationError(VALIDATION_ITEM_BEING_USED,"id","Department",persistentObject.getId());
    }

}
