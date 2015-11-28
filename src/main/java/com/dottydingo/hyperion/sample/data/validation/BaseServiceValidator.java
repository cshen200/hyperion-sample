package com.dottydingo.hyperion.sample.data.validation;

import com.dottydingo.hyperion.core.validation.DefaultValidator;

/**
 */
public class BaseServiceValidator<C,P> extends DefaultValidator<C,P>
{
    protected static final String VALIDATION_VALUE_NOT_UNIQUE = "VALUE_NOT_UNIQUE";
    protected static final String VALIDATION_ITEM_BEING_USED = "ITEM_BEING_USED";
    protected static final String VALIDATION_NOT_FOUND = "NOT_FOUND";
    protected static final String VALIDATION_NOT_FOUND_IDS = "NOT_FOUND_IDS";
}
