package com.dottydingo.hyperion.sample.data.validation;

import com.dottydingo.hyperion.sample.data.api.Department;
import com.dottydingo.hyperion.sample.data.model.PersistentDepartment;
import com.dottydingo.hyperion.sample.data.persistence.DepartmentLoader;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 */
public class DepartmentValidatorTest extends BaseValidationFixture
{

    private DepartmentValidator validator = new DepartmentValidator();
    private DepartmentLoader departmentLoader;

    @Before
    public void setUp() throws Exception
    {
        super.setup();
        departmentLoader = Mockito.mock(DepartmentLoader.class);
        validator.setDepartmentLoader(departmentLoader);
        Mockito.when(departmentLoader.isUnique(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
        Mockito.when(departmentLoader.isUnique("name","not unique")).thenReturn(false);
    }

    @Test
    public void testCreate()
    {
        // validate happy path
        Department client = new Department();
        client.setName(RandomStringUtils.randomAlphanumeric(64));

        validator.validateCreate(client,errorContext,persistenceContext);
        assertFalse(errorContext.hasErrors());
    }

    @Test
    public void testCreate_Required()
    {
        // validate required fields
        Department client = new Department();

        validator.validateCreate(client,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertTrue(errorContext.containsError(DepartmentValidator.REQUIRED_FIELD,"name"));
    }

    @Test
    public void testCreate_Length()
    {
        // validate field length
        Department client = new Department();
        client.setName(RandomStringUtils.randomAlphanumeric(65));

        validator.validateCreate(client,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertTrue(errorContext.containsError(DepartmentValidator.FIELD_LENGTH,"name"));
    }

    @Test
    public void testCreate_Unique()
    {
        // validate name is unique
        Department client = new Department();
        client.setName("not unique");

        validator.validateCreateConflict(client,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertTrue(errorContext.containsError(DepartmentValidator.VALIDATION_VALUE_NOT_UNIQUE,"name"));
    }

    @Test
    public void testUpdate()
    {
        // validate happy path
        Department client = new Department();
        client.setName(RandomStringUtils.randomAlphanumeric(64));

        PersistentDepartment persistent = new PersistentDepartment();

        validator.validateUpdate(client,persistent,errorContext,persistenceContext);
        assertFalse(errorContext.hasErrors());
    }

    @Test
    public void testUpdate_Length()
    {
        // validate field length
        Department client = new Department();
        client.setName(RandomStringUtils.randomAlphanumeric(65));

        PersistentDepartment persistent = new PersistentDepartment();

        validator.validateUpdate(client,persistent,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertTrue(errorContext.containsError(DepartmentValidator.FIELD_LENGTH,"name"));
    }

    @Test
    public void testUpdate_Unique()
    {
        // validate name is unique
        Department client = new Department();
        client.setName("unique");

        PersistentDepartment persistent = new PersistentDepartment();
        persistent.setName("unique");

        // the value has not changed, no check should be made
        validator.validateUpdateConflict(client,persistent,errorContext,persistenceContext);
        assertFalse(errorContext.hasErrors());

        // now the value has changed, the check should be made
        client.setName("not unique");
        validator.validateUpdateConflict(client,persistent,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertTrue(errorContext.containsError(DepartmentValidator.VALIDATION_VALUE_NOT_UNIQUE,"name"));
    }

    @Test
    public void testDelete_Referenced()
    {
        PersistentDepartment persistent = new PersistentDepartment();
        persistent.setId(10L);

        Mockito.when(departmentLoader.isReferenced(10L)).thenReturn(false);
        Mockito.when(departmentLoader.isReferenced(20L)).thenReturn(true);

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