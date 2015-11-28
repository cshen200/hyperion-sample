package com.dottydingo.hyperion.sample.data.translation;

import com.dottydingo.hyperion.sample.data.Util;
import com.dottydingo.hyperion.sample.data.api.Employee;
import com.dottydingo.hyperion.sample.data.model.PersistentEmployee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 */
public class EmployeeTranslatorTest extends BaseTranslatorFixture
{
    private EmployeeTranslator translator;

    @Before
    public void setup()
    {
        super.setup();
        translator = new EmployeeTranslator();
        translator.init();
    }

    @Test
    public void testConvertClient()
    {
        Employee client = new Employee();
        client.setFirstName("first name");
        client.setLastName("last name");
        client.setLastName("last name");
        client.setTitle("title");
        client.setDateOfBirth(buildDate("2015-02-03"));
        client.setDateOfHire(buildDate("2014-10-09"));
        client.setAddress("address");
        client.setCity("city");
        client.setState("state");
        client.setPostalCode("zip");
        client.setHomePhone("phone");
        client.setSupervisorId(10L);
        client.setDepartmentId(20L);
        client.setTerritoryIds(Util.asList(30L,31L));

        PersistentEmployee persistent = translator.convertClient(client,context);
        assertNotNull(persistent);
        assertEquals("first name",persistent.getFirstName());
        assertEquals("last name",persistent.getLastName());
        assertEquals("title",persistent.getTitle());
        assertEquals(buildDate("2015-02-03"),persistent.getDateOfBirth());
        assertEquals(buildDate("2014-10-09"),persistent.getDateOfHire());
        assertEquals("address",persistent.getAddress());
        assertEquals("city",persistent.getCity());
        assertEquals("state",persistent.getState());
        assertEquals("zip",persistent.getPostalCode());
        assertEquals("phone",persistent.getHomePhone());
        assertEquals(new Long(10L),persistent.getSupervisorId());
        assertEquals(new Long(20L),persistent.getDepartmentId());
        assertEquals(Util.asList(30L,31L),persistent.getTerritoryIds());
    }


    @Test
    public void testConvertPersistent()
    {
        PersistentEmployee persistent = new PersistentEmployee();
        persistent.setFirstName("first name");
        persistent.setLastName("last name");
        persistent.setLastName("last name");
        persistent.setTitle("title");
        persistent.setDateOfBirth(buildDate("2015-02-03"));
        persistent.setDateOfHire(buildDate("2014-10-09"));
        persistent.setAddress("address");
        persistent.setCity("city");
        persistent.setState("state");
        persistent.setPostalCode("zip");
        persistent.setHomePhone("phone");
        persistent.setSupervisorId(10L);
        persistent.setDepartmentId(20L);
        persistent.setTerritoryIds(Util.asList(30L,31L));

        Employee client = translator.convertPersistent(persistent,context);
        assertNotNull(client);
        assertEquals("first name",client.getFirstName());
        assertEquals("last name",client.getLastName());
        assertEquals("title",client.getTitle());
        assertEquals(buildDate("2015-02-03"),client.getDateOfBirth());
        assertEquals(buildDate("2014-10-09"),client.getDateOfHire());
        assertEquals("address",client.getAddress());
        assertEquals("city",client.getCity());
        assertEquals("state",client.getState());
        assertEquals("zip",client.getPostalCode());
        assertEquals("phone",client.getHomePhone());
        assertEquals(new Long(10L),client.getSupervisorId());
        assertEquals(new Long(20L),client.getDepartmentId());
        assertEquals(Util.asList(30L,31L),client.getTerritoryIds());
    }
}