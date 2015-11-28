package com.dottydingo.hyperion.sample.data.validation;

import com.dottydingo.hyperion.sample.data.Util;
import com.dottydingo.hyperion.sample.data.api.Employee;
import com.dottydingo.hyperion.sample.data.model.PersistentEmployee;
import com.dottydingo.hyperion.sample.data.persistence.DepartmentLoader;
import com.dottydingo.hyperion.sample.data.persistence.EmployeeLoader;
import com.dottydingo.hyperion.sample.data.persistence.TerritoryLoader;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 */
public class EmployeeValidatorTest extends BaseValidationFixture
{
    private EmployeeValidator validator = new EmployeeValidator();
    private EmployeeLoader employeeLoader;
    private DepartmentLoader departmentLoader;
    private TerritoryLoader territoryLoader;


    @Before
    public void setUp() throws Exception
    {
        super.setup();
        departmentLoader = Mockito.mock(DepartmentLoader.class);
        validator.setDepartmentLoader(departmentLoader);
        Mockito.when(departmentLoader.exists(10L)).thenReturn(true);

        employeeLoader = Mockito.mock(EmployeeLoader.class);
        validator.setEmployeeLoader(employeeLoader);
        Mockito.when(employeeLoader.exists(20L)).thenReturn(true);

        territoryLoader = Mockito.mock(TerritoryLoader.class);
        validator.setTerritoryLoader(territoryLoader);
        Mockito.when(territoryLoader.exists(Matchers.argThat(CollectionMatcher.contains(Util.asList(30L,31L,33L))))).thenReturn(
                Util.asList(30L,31L,33L));
        Mockito.when(territoryLoader.exists(Util.asList(35L,36L,37L))).thenReturn(Util.asList(35L));
    }

    @Test
    public void testCreate()
    {
        // validate happy path
        Employee client = new Employee();
        client.setFirstName(RandomStringUtils.randomAlphabetic(64));
        client.setLastName(RandomStringUtils.randomAlphabetic(64));
        client.setTitle(RandomStringUtils.randomAlphabetic(64));
        client.setDateOfBirth(new Date());
        client.setDateOfHire(new Date());
        client.setAddress(RandomStringUtils.randomAlphabetic(128));
        client.setCity(RandomStringUtils.randomAlphabetic(64));
        client.setState(RandomStringUtils.randomAlphabetic(2));
        client.setPostalCode(RandomStringUtils.randomAlphabetic(10));
        client.setHomePhone(RandomStringUtils.randomAlphabetic(10));
        client.setDepartmentId(10L);
        client.setSupervisorId(20L);
        client.setTerritoryIds(Util.asList(30L,31L,33L));

        validator.validateCreate(client,errorContext,persistenceContext);
        assertFalse(errorContext.hasErrors());
    }

    @Test
    public void testCreate_Required()
    {
        // validate required fields
        Employee client = new Employee();

        validator.validateCreate(client,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertEquals(11,errorContext.getValidationErrors().size());
        assertTrue(errorContext.containsError(EmployeeValidator.REQUIRED_FIELD,"firstName"));
        assertTrue(errorContext.containsError(EmployeeValidator.REQUIRED_FIELD,"lastName"));
        assertTrue(errorContext.containsError(EmployeeValidator.REQUIRED_FIELD,"title"));
        assertTrue(errorContext.containsError(EmployeeValidator.REQUIRED_FIELD,"dateOfBirth"));
        assertTrue(errorContext.containsError(EmployeeValidator.REQUIRED_FIELD,"dateOfHire"));
        assertTrue(errorContext.containsError(EmployeeValidator.REQUIRED_FIELD,"address"));
        assertTrue(errorContext.containsError(EmployeeValidator.REQUIRED_FIELD,"city"));
        assertTrue(errorContext.containsError(EmployeeValidator.REQUIRED_FIELD,"state"));
        assertTrue(errorContext.containsError(EmployeeValidator.REQUIRED_FIELD,"postalCode"));
        assertTrue(errorContext.containsError(EmployeeValidator.REQUIRED_FIELD,"homePhone"));
        assertTrue(errorContext.containsError(EmployeeValidator.REQUIRED_FIELD,"departmentId"));
    }

    @Test
    public void testCreate_Length()
    {
        // validate required fields
        Employee client = new Employee();
        client.setDepartmentId(10L);
        client.setDateOfHire(new Date());
        client.setDateOfBirth(new Date());

        client.setFirstName(RandomStringUtils.randomAlphanumeric(65));
        client.setLastName(RandomStringUtils.randomAlphanumeric(65));
        client.setTitle(RandomStringUtils.randomAlphanumeric(65));
        client.setAddress(RandomStringUtils.randomAlphanumeric(129));
        client.setCity(RandomStringUtils.randomAlphanumeric(65));
        client.setState(RandomStringUtils.randomAlphanumeric(3));
        client.setPostalCode(RandomStringUtils.randomAlphanumeric(11));
        client.setHomePhone(RandomStringUtils.randomAlphanumeric(11));

        validator.validateCreate(client,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertEquals(8,errorContext.getValidationErrors().size());
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"firstName"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"lastName"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"title"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"address"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"city"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"state"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"postalCode"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"homePhone"));
    }

    @Test
    public void testCreate_References()
    {
        // validate referential integrity checks
        // validate required fields
        Employee client = new Employee();
        client.setFirstName(RandomStringUtils.randomAlphabetic(64));
        client.setLastName(RandomStringUtils.randomAlphabetic(64));
        client.setTitle(RandomStringUtils.randomAlphabetic(64));
        client.setDateOfBirth(new Date());
        client.setDateOfHire(new Date());
        client.setAddress(RandomStringUtils.randomAlphabetic(128));
        client.setCity(RandomStringUtils.randomAlphabetic(64));
        client.setState(RandomStringUtils.randomAlphabetic(2));
        client.setPostalCode(RandomStringUtils.randomAlphabetic(10));
        client.setHomePhone(RandomStringUtils.randomAlphabetic(10));

        client.setDepartmentId(11L);
        client.setSupervisorId(21L);
        client.setTerritoryIds(Util.asList(35L,36L,37L));

        validator.validateCreate(client,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertEquals(3,errorContext.getValidationErrors().size());
        assertTrue(errorContext.containsError(EmployeeValidator.VALIDATION_NOT_FOUND,"departmentId"));
        assertTrue(errorContext.containsError(EmployeeValidator.VALIDATION_NOT_FOUND,"supervisorId"));
        assertTrue(errorContext.containsError(EmployeeValidator.VALIDATION_NOT_FOUND_IDS,"territoryIds"));
    }

    @Test
    public void testUpdate()
    {
        // validate happy path
        Employee client = new Employee();
        client.setFirstName(RandomStringUtils.randomAlphabetic(64));
        client.setLastName(RandomStringUtils.randomAlphabetic(64));
        client.setTitle(RandomStringUtils.randomAlphabetic(64));
        client.setDateOfBirth(new Date());
        client.setDateOfHire(new Date());
        client.setAddress(RandomStringUtils.randomAlphabetic(128));
        client.setCity(RandomStringUtils.randomAlphabetic(64));
        client.setState(RandomStringUtils.randomAlphabetic(2));
        client.setPostalCode(RandomStringUtils.randomAlphabetic(10));
        client.setHomePhone(RandomStringUtils.randomAlphabetic(10));
        client.setDepartmentId(10L);
        client.setSupervisorId(20L);
        client.setTerritoryIds(Util.asList(30L,31L,33L));

        PersistentEmployee persistent = new PersistentEmployee();

        validator.validateUpdate(client,persistent,errorContext,persistenceContext);
        assertFalse(errorContext.hasErrors());
    }

    @Test
    public void testUpdate_Length()
    {
        // validate required fields
        Employee client = new Employee();

        client.setFirstName(RandomStringUtils.randomAlphanumeric(65));
        client.setLastName(RandomStringUtils.randomAlphanumeric(65));
        client.setTitle(RandomStringUtils.randomAlphanumeric(65));
        client.setAddress(RandomStringUtils.randomAlphanumeric(129));
        client.setCity(RandomStringUtils.randomAlphanumeric(65));
        client.setState(RandomStringUtils.randomAlphanumeric(3));
        client.setPostalCode(RandomStringUtils.randomAlphanumeric(11));
        client.setHomePhone(RandomStringUtils.randomAlphanumeric(11));

        PersistentEmployee persistent = new PersistentEmployee();

        validator.validateUpdate(client,persistent,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertEquals(8,errorContext.getValidationErrors().size());
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"firstName"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"lastName"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"title"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"address"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"city"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"state"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"postalCode"));
        assertTrue(errorContext.containsError(EmployeeValidator.FIELD_LENGTH,"homePhone"));
    }

    @Test
    public void testUpdate_References()
    {
        // validate referential integrity checks
        // validate required fields
        Employee client = new Employee();
        client.setDepartmentId(11L);
        client.setSupervisorId(21L);
        client.setTerritoryIds(Util.asList(35L,36L,37L));

        PersistentEmployee persistent = new PersistentEmployee();
        persistent.setDepartmentId(10L);
        persistent.setSupervisorId(20L);

        validator.validateUpdate(client,persistent,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertEquals(3,errorContext.getValidationErrors().size());
        assertTrue(errorContext.containsError(EmployeeValidator.VALIDATION_NOT_FOUND,"departmentId"));
        assertTrue(errorContext.containsError(EmployeeValidator.VALIDATION_NOT_FOUND,"supervisorId"));
        assertTrue(errorContext.containsError(EmployeeValidator.VALIDATION_NOT_FOUND_IDS,"territoryIds"));
    }

    @Test
    public void testUpdate_SuperviseSelf()
    {
        // validate referential integrity checks
        // validate required fields
        Employee client = new Employee();
        client.setSupervisorId(100L);

        PersistentEmployee persistent = new PersistentEmployee();
        persistent.setId(100L);
        persistent.setSupervisorId(20L);

        validator.validateUpdate(client,persistent,errorContext,persistenceContext);
        assertTrue(errorContext.hasErrors());
        assertEquals(1,errorContext.getValidationErrors().size());
        assertTrue(errorContext.containsError(EmployeeValidator.VALIDATION_SUPERVISE_SELF,"supervisorId"));
    }

    private static class CollectionMatcher<T> extends BaseMatcher<Collection<T>>
    {
        private static <T> CollectionMatcher<T> contains(Collection<T> collection)
        {
            return new CollectionMatcher<T>(collection);
        }

        private Collection<T> collection;

        public CollectionMatcher(Collection collection)
        {
            this.collection = new HashSet<T>(collection);
        }

        @Override
        public boolean matches(Object o)
        {
            return collection.equals(o);
        }

        @Override
        public void describeTo(Description description)
        {

        }
    }
}
