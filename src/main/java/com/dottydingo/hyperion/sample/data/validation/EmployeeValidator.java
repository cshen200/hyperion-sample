package com.dottydingo.hyperion.sample.data.validation;

import com.dottydingo.hyperion.core.persistence.PersistenceContext;
import com.dottydingo.hyperion.core.validation.ValidationErrorContext;
import com.dottydingo.hyperion.sample.data.api.Employee;
import com.dottydingo.hyperion.sample.data.model.PersistentEmployee;
import com.dottydingo.hyperion.sample.data.persistence.DepartmentLoader;
import com.dottydingo.hyperion.sample.data.persistence.EmployeeLoader;
import com.dottydingo.hyperion.sample.data.persistence.TerritoryLoader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 */
public class EmployeeValidator extends BaseServiceValidator<Employee,PersistentEmployee>
{
    protected static final String VALIDATION_SUPERVISE_SELF = "SUPERVISE_SELF";

    @Autowired
    private DepartmentLoader departmentLoader;
    @Autowired
    private TerritoryLoader territoryLoader;
    @Autowired
    private EmployeeLoader employeeLoader;

    // for testing
    void setDepartmentLoader(DepartmentLoader departmentLoader)
    {
        this.departmentLoader = departmentLoader;
    }

    // for testing
    void setTerritoryLoader(TerritoryLoader territoryLoader)
    {
        this.territoryLoader = territoryLoader;
    }

    // for testing
    public void setEmployeeLoader(EmployeeLoader employeeLoader)
    {
        this.employeeLoader = employeeLoader;
    }

    @Override
    protected void validateCreate(Employee clientObject, ValidationErrorContext errorContext,
                                  PersistenceContext persistenceContext)
    {
        validateRequired(errorContext,"firstName",clientObject.getFirstName());
        validateLength(errorContext,"firstName",clientObject.getFirstName(),64);

        validateRequired(errorContext,"lastName",clientObject.getLastName());
        validateLength(errorContext,"lastName",clientObject.getLastName(),64);

        validateRequired(errorContext,"title",clientObject.getTitle());
        validateLength(errorContext,"title",clientObject.getTitle(),64);

        validateRequired(errorContext,"dateOfBirth",clientObject.getDateOfBirth());
        validateRequired(errorContext,"dateOfHire",clientObject.getDateOfHire());

        validateRequired(errorContext,"address",clientObject.getAddress());
        validateLength(errorContext,"address",clientObject.getAddress(),128);

        validateRequired(errorContext,"city",clientObject.getCity());
        validateLength(errorContext,"city",clientObject.getCity(),64);

        validateRequired(errorContext,"state",clientObject.getState());
        validateLength(errorContext,"state",clientObject.getState(),2);

        validateRequired(errorContext,"postalCode",clientObject.getPostalCode());
        validateLength(errorContext,"postalCode",clientObject.getPostalCode(),10);

        validateRequired(errorContext,"homePhone",clientObject.getHomePhone());
        validateLength(errorContext,"homePhone",clientObject.getHomePhone(),10);

        validateRequired(errorContext,"departmentId",clientObject.getDepartmentId());
        if(clientObject.getDepartmentId() != null && !departmentLoader.exists(clientObject.getDepartmentId()))
            errorContext.addValidationError(VALIDATION_NOT_FOUND,"departmentId","Department",clientObject.getDepartmentId());

        if(clientObject.getSupervisorId() != null && !employeeLoader.exists(clientObject.getSupervisorId()))
            errorContext.addValidationError(VALIDATION_NOT_FOUND,"supervisorId","Employee",clientObject.getSupervisorId());

        validateTerritoryIds(clientObject.getTerritoryIds(),errorContext);
    }

    @Override
    protected void validateUpdate(Employee clientObject, PersistentEmployee persistentObject,
                                  ValidationErrorContext errorContext, PersistenceContext persistenceContext)
    {
        validateLength(errorContext,"firstName",clientObject.getFirstName(),64);

        validateLength(errorContext,"lastName",clientObject.getLastName(),64);

        validateLength(errorContext,"title",clientObject.getTitle(),64);

        validateLength(errorContext,"address",clientObject.getAddress(),128);

        validateLength(errorContext,"city",clientObject.getCity(),64);

        validateLength(errorContext,"state",clientObject.getState(),2);

        validateLength(errorContext,"postalCode",clientObject.getPostalCode(),10);

        validateLength(errorContext,"homePhone",clientObject.getHomePhone(),10);

        if(valueChanged(clientObject.getDepartmentId(),persistentObject.getDepartmentId())
                && !departmentLoader.exists(clientObject.getDepartmentId()))
            errorContext.addValidationError(VALIDATION_NOT_FOUND,"departmentId","Department",clientObject.getDepartmentId());

        if(valueChanged(clientObject.getSupervisorId(),persistentObject.getSupervisorId()))
        {
            if(clientObject.getSupervisorId().equals(persistentObject.getId()))
                errorContext.addValidationError(VALIDATION_SUPERVISE_SELF,"supervisorId");
            else if(!employeeLoader.exists(clientObject.getSupervisorId()))
                errorContext.addValidationError(VALIDATION_NOT_FOUND, "supervisorId", "Employee",
                        clientObject.getSupervisorId());
        }

        validateTerritoryIds(clientObject.getTerritoryIds(),errorContext);
    }

    protected void validateTerritoryIds(Collection<Long> territoryIds,ValidationErrorContext errorContext)
    {
        if(territoryIds == null || territoryIds.isEmpty())
            return;

        Set<Long> unique = new LinkedHashSet<>(territoryIds);
        List<Long> found = territoryLoader.exists(unique);
        if(unique.size() != found.size())
        {
            unique.removeAll(found);
            errorContext.addValidationError(VALIDATION_NOT_FOUND_IDS,"territoryIds","Territory", StringUtils.join(unique,","));
        }

    }

}
