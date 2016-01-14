package org.ppa.xasx.core.matcher;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.ppa.xasx.core.ValueIOContext;
import org.ppa.xasx.core.ValueNodeReader;

public class AttributeMatcherTest {

    @Test
    public void standardTest() {
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            ValueIOContext context = new ValueIOContext();
            ValueNodeReader reader = mock(ValueNodeReader.class);
            context.setValueNodeReader(reader);

            when(reader.getAttribute(o, "attr", context)).thenReturn("val");

            assertTrue(matcher.match(o, context));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            ValueIOContext context = new ValueIOContext();
            ValueNodeReader reader = mock(ValueNodeReader.class);
            context.setValueNodeReader(reader);

            when(reader.getAttribute(o, "attr", context)).thenReturn("va");

            assertFalse(matcher.match(o, context));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            ValueIOContext context = new ValueIOContext();
            ValueNodeReader reader = mock(ValueNodeReader.class);
            context.setValueNodeReader(reader);

            when(reader.getAttribute(o, "attr", context)).thenReturn("vals");

            assertFalse(matcher.match(o, context));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            ValueIOContext context = new ValueIOContext();
            ValueNodeReader reader = mock(ValueNodeReader.class);
            context.setValueNodeReader(reader);

            when(reader.getAttribute(o, "attr1", context)).thenReturn("val");

            assertFalse(matcher.match(o, context));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            ValueIOContext context = new ValueIOContext();
            ValueNodeReader reader = mock(ValueNodeReader.class);
            context.setValueNodeReader(reader);

            when(reader.getAttribute(o, "att", context)).thenReturn("val");

            assertFalse(matcher.match(o, context));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            ValueIOContext context = new ValueIOContext();
            ValueNodeReader reader = mock(ValueNodeReader.class);
            context.setValueNodeReader(reader);

            when(reader.getAttribute(o, "attr", context)).thenReturn("val");
            when(reader.getAttribute(o, "attr2", context)).thenReturn("val");

            assertTrue(matcher.match(o, context));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            ValueIOContext context = new ValueIOContext();
            ValueNodeReader reader = mock(ValueNodeReader.class);
            context.setValueNodeReader(reader);

            when(reader.getAttribute(o, "attr", context)).thenReturn("val2");
            when(reader.getAttribute(o, "attr2", context)).thenReturn("val");

            assertFalse(matcher.match(o, context));
        }
    }

}
