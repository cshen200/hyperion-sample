package com.dottydingo.hyperion.sample.data.translation;

import com.dottydingo.hyperion.core.endpoint.pipeline.auth.NoOpAuthorizationContext;
import com.dottydingo.hyperion.core.persistence.PersistenceContext;
import com.dottydingo.service.endpoint.context.UserContext;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Date;

/**
 */
public class BaseTranslatorFixture
{
    private static DateTimeFormatter formatter = ISODateTimeFormat.date();
    protected PersistenceContext context;

    protected void setup()
    {
        context = new PersistenceContext();
        context.setUserContext(new UserContext());
        context.setAuthorizationContext(new NoOpAuthorizationContext(null));
    }

    protected Date buildDate(String date)
    {
        return formatter.parseDateTime(date).toDate();
    }
}
