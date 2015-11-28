package com.dottydingo.hyperion.sample.data.translation;

import com.dottydingo.hyperion.sample.data.api.Territory;
import com.dottydingo.hyperion.sample.data.model.PersistentTerritory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 */
public class TerritoryTranslatorTest extends BaseTranslatorFixture
{
    private TerritoryTranslator translator;

    @Before
    public void setup()
    {
        super.setup();
        translator = new TerritoryTranslator();
        translator.init();
    }

    @Test
    public void testConvertClient()
    {
        Territory client = new Territory();
        client.setName("name");

        PersistentTerritory persistent = translator.convertClient(client,context);
        assertNotNull(persistent);
        assertEquals("name",persistent.getName());
    }


    @Test
    public void testConvertPersistent()
    {
        PersistentTerritory persistent = new PersistentTerritory();
        persistent.setName("name");

        Territory client = translator.convertPersistent(persistent,context);
        assertNotNull(client);
        assertEquals("name",client.getName());
    }
}