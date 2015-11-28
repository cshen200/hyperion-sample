package com.dottydingo.hyperion.sample.data.validation;

import com.dottydingo.hyperion.core.persistence.PersistenceContext;
import com.dottydingo.hyperion.core.validation.ValidationErrorContext;

import java.util.Arrays;
import java.util.List;

/**
 */
public abstract class BaseValidationFixture
{
    protected PersistenceContext persistenceContext;
    protected ValidationErrorContext errorContext;

    protected void setup()
    {
        persistenceContext = new PersistenceContext();
        errorContext = new ValidationErrorContext();
    }

}
