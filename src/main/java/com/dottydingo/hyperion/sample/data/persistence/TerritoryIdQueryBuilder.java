package com.dottydingo.hyperion.sample.data.persistence;

import com.dottydingo.hyperion.core.persistence.PersistenceContext;
import com.dottydingo.hyperion.jpa.persistence.query.AbstractEntityJpaQueryBuilder;
import com.dottydingo.hyperion.jpa.persistence.query.ComparisonOperator;
import com.dottydingo.hyperion.sample.data.model.PersistentEmployee;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Add support to query for Employees who have specified territory IDs assocated with them.
 */
public class TerritoryIdQueryBuilder extends AbstractEntityJpaQueryBuilder<PersistentEmployee>
{
    @Override
    public Predicate buildPredicate(From root, CriteriaQuery<?> query, CriteriaBuilder cb, ComparisonOperator operator,
                                    List<String> arguments, PersistenceContext persistenceContext)
    {
        Object parsed = operator.supportsMultipleArguments()
                ? argumentParser.parse(arguments,Long.class, persistenceContext)
                : argumentParser.parse(arguments.get(0),Long.class, persistenceContext);


        Subquery idQuery = query.subquery(Long.class);
        idQuery.distinct(true);
        Root idRoot = idQuery.from(PersistentEmployee.class);
        idQuery.select(idRoot.get("id"));
        ListJoin idFrom = idRoot.joinList("territoryIds");


        Collection values = null;
        if(parsed instanceof Collection)
            values = (Collection) parsed;
        else
            values = Collections.singletonList(parsed);

        idQuery.where(idFrom.in(values));


        //subFr.distinct(true);
        switch (operator)
        {
            case EQUAL:
            case IN:
                return root.get("id").in(idQuery);
            case NOT_EQUAL:
            case NOT_IN:
                return cb.not(root.get("id").in(idQuery));
        }

        throw new IllegalArgumentException("Unsupported operator: " + operator);
    }
}
