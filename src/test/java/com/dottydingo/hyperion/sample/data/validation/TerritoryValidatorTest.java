package com.dottydingo.hyperion.sample.data.validation;

import com.dottydingo.hyperion.sample.data.api.Territory;
import com.dottydingo.hyperion.sample.data.model.PersistentTerritory;
import com.dottydingo.hyperion.sample.data.persistence.TerritoryLoader;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 */
public class TerritoryValidatorTest extends BaseValidationFixture
{

    private TerritoryValidator validator = new TerritoryValidator();
    private TerritoryLoader territoryLoader;

    @Before
    public void setUp() throws Exception
    {
        super.setup();
        territoryLoader = Mockito.mock(TerritoryLoader.class);
        validator.setTerritoryLoader(territoryLoader);
        Mockito.when(territoryLoader.isUnique(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
        Mockito.when(territoryLoader.isUnique("name","not unique")).thenReturn(false);
    }

    @Test
    public void testCreate()
    {
        // validate happy path
        Territory client = new Territory();
        client.setName(RandomStringUtils.randomAlphanumeric(64));

        validator.validateCreate(client,errorContext,persistenceContext);
        assertFalse(errorContext.hasErrors());
    }

    @Test
    public void testCreate_Required()
    {
        // validate required fields
        Territory client = new Territory();

        validator.validateCreate(client,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertTrue(errorContext.containsError(TerritoryValidator.REQUIRED_FIELD,"name"));
    }

    @Test
    public void testCreate_Length()
    {
        // validate field length
        Territory client = new Territory();
        client.setName(RandomStringUtils.randomAlphanumeric(65));

        validator.validateCreate(client,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertTrue(errorContext.containsError(TerritoryValidator.FIELD_LENGTH,"name"));
    }

    @Test
    public void testCreate_Unique()
    {
        // validate name is unique
        Territory client = new Territory();
        client.setName("not unique");

        validator.validateCreateConflict(client,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertTrue(errorContext.containsError(TerritoryValidator.VALIDATION_VALUE_NOT_UNIQUE,"name"));
    }

    @Test
    public void testUpdate()
    {
        // validate happy path
        Territory client = new Territory();
        client.setName(RandomStringUtils.randomAlphanumeric(64));

        PersistentTerritory persistent = new PersistentTerritory();

        validator.validateUpdate(client,persistent,errorContext,persistenceContext);
        assertFalse(errorContext.hasErrors());
    }

    @Test
    public void testUpdate_Length()
    {
        // validate field length
        Territory client = new Territory();
        client.setName(RandomStringUtils.randomAlphanumeric(65));

        PersistentTerritory persistent = new PersistentTerritory();

        validator.validateUpdate(client,persistent,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertTrue(errorContext.containsError(TerritoryValidator.FIELD_LENGTH,"name"));
    }

    @Test
    public void testUpdate_Unique()
    {
        // validate name is unique
        Territory client = new Territory();
        client.setName("unique");

        PersistentTerritory persistent = new PersistentTerritory();
        persistent.setName("unique");

        // the value has not changed, no check should be made
        validator.validateUpdateConflict(client,persistent,errorContext,persistenceContext);
        assertFalse(errorContext.hasErrors());

        // now the value has changed, the check should be made
        client.setName("not unique");
        validator.validateUpdateConflict(client,persistent,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertTrue(errorContext.containsError(TerritoryValidator.VALIDATION_VALUE_NOT_UNIQUE,"name"));
    }

    @Test
    public void testDelete_Referenced()
    {
        PersistentTerritory persistent = new PersistentTerritory();
        persistent.setId(10L);

        Mockito.when(territoryLoader.isReferenced(10L)).thenReturn(false);
        Mockito.when(territoryLoader.isReferenced(20L)).thenReturn(true);

        // the value is not being referenced
        validator.validateDeleteConflict(persistent,errorContext,persistenceContext);
        assertFalse(errorContext.hasErrors());

        // now the value has changed, the check should be made
        persistent.setId(20L);
        validator.validateDeleteConflict(persistent,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertTrue(errorContext.containsError(DepartmentValidator.VALIDATION_ITEM_BEING_USED,"id"));
    }
}