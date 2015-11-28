package com.dottydingo.hyperion.sample.data.translation;

import com.dottydingo.hyperion.sample.data.api.Department;
import com.dottydingo.hyperion.sample.data.model.PersistentDepartment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class DepartmentTranslatorTest extends BaseTranslatorFixture
{
    private DepartmentTranslator translator;

    @Before
    public void setup()
    {
        super.setup();
        translator = new DepartmentTranslator();
        translator.init();
    }

    @Test
    public void testConvertClient()
    {
        Department client = new Department();
        client.setName("name");

        PersistentDepartment persistent = translator.convertClient(client,context);
        assertNotNull(persistent);
        assertEquals("name",persistent.getName());
    }


    @Test
    public void testConvertPersistent()
    {
        PersistentDepartment persistent = new PersistentDepartment();
        persistent.setName("name");

        Department client = translator.convertPersistent(persistent,context);
        assertNotNull(client);
        assertEquals("name",client.getName());
    }
}