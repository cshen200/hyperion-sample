package com.dottydingo.hyperion.sample.data.translation;

import com.dottydingo.hyperion.core.translation.DefaultAuditingTranslator;
import com.dottydingo.hyperion.core.translation.DefaultFieldMapper;
import com.dottydingo.hyperion.core.translation.FieldMapper;
import com.dottydingo.hyperion.sample.data.api.Employee;
import com.dottydingo.hyperion.sample.data.model.PersistentEmployee;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class EmployeeTranslator extends DefaultAuditingTranslator<Employee,PersistentEmployee>
{
    public EmployeeTranslator()
    {
        super(Employee.class, PersistentEmployee.class);
    }

    @Override
    protected List<FieldMapper> getCustomFieldMappers()
    {
        List<FieldMapper> fieldMappers = new ArrayList<>();
        fieldMappers.addAll(super.getCustomFieldMappers());

        // set a property change evaluator to deal with bag-->collection equality checks
        DefaultFieldMapper territoryIdMapper = new DefaultFieldMapper("territoryIds", "territoryIds", new LazyCollectionValueConverter());
        territoryIdMapper.setPropertyChangeEvaluator(new BagPropertyChangeEvaluator());
        fieldMappers.add(territoryIdMapper);

        // deal with date equals checks
        DefaultFieldMapper hireDateMapper = new DefaultFieldMapper("dateOfHire");
        hireDateMapper.setPropertyChangeEvaluator(new DatePropertyChangeEvaluator());
        fieldMappers.add(hireDateMapper);

        DefaultFieldMapper birthDateMapper = new DefaultFieldMapper("dateOfBirth");
        birthDateMapper.setPropertyChangeEvaluator(new DatePropertyChangeEvaluator());
        fieldMappers.add(birthDateMapper);

        return fieldMappers;
    }
}
