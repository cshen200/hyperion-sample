package com.dottydingo.hyperion.sample.data.validation;

import com.dottydingo.hyperion.core.persistence.PersistenceContext;
import com.dottydingo.hyperion.core.validation.ValidationErrorContext;
import com.dottydingo.hyperion.sample.data.api.Territory;
import com.dottydingo.hyperion.sample.data.model.PersistentTerritory;
import com.dottydingo.hyperion.sample.data.persistence.TerritoryLoader;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Validate Departments
 */
public class TerritoryValidator extends BaseServiceValidator<Territory,PersistentTerritory>
{
    @Autowired
    private TerritoryLoader territoryLoader;

    // for testing
    void setTerritoryLoader(TerritoryLoader territoryLoader)
    {
        this.territoryLoader = territoryLoader;
    }

    @Override
    protected void validateCreateConflict(Territory clientObject, ValidationErrorContext errorContext, PersistenceContext persistenceContext)
    {
        if(clientObject.getName() != null && !territoryLoader.isUnique("name",clientObject.getName()))
            errorContext.addValidationError(VALIDATION_VALUE_NOT_UNIQUE,"name","Territory","name",clientObject.getName());
    }

    @Override
    protected void validateCreate(Territory clientObject, ValidationErrorContext errorContext, PersistenceContext persistenceContext)
    {
        validateRequired(errorContext,"name",clientObject.getName());
        validateLength(errorContext,"name",clientObject.getName(),64);
    }

    @Override
    protected void validateUpdateConflict(Territory clientObject, PersistentTerritory persistentObject,
                                          ValidationErrorContext errorContext, PersistenceContext persistenceContext)
    {
        if(valueChanged(clientObject.getName(),persistentObject.getName())
                && !territoryLoader.isUnique("name",clientObject.getName()))
            errorContext.addValidationError(VALIDATION_VALUE_NOT_UNIQUE,"name","Territory","name",clientObject.getName());
    }

    @Override
    protected void validateUpdate(Territory clientObject, PersistentTerritory persistentObject,
                                  ValidationErrorContext errorContext, PersistenceContext persistenceContext)
    {
        validateLength(errorContext,"name",clientObject.getName(),64);
    }

    @Override
    protected void validateDeleteConflict(PersistentTerritory persistentObject, ValidationErrorContext errorContext,
                                          PersistenceContext persistenceContext)
    {
        if(territoryLoader.isReferenced(persistentObject.getId()))
            errorContext.addValidationError(VALIDATION_ITEM_BEING_USED,"id","Territory",persistentObject.getId());
    }

}
