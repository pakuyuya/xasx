package org.ppa.xasx.core.matcher;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.ppa.xasx.types.NodeReadWriter;

public class AttributeMatcherTest {

    @Test
    public void standardTest() {
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            NodeReadWriter reader = mock(NodeReadWriter.class);

            when(reader.getAttribute(o, "attr")).thenReturn("val");

            assertTrue(matcher.match(o, reader));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            NodeReadWriter reader = mock(NodeReadWriter.class);

            when(reader.getAttribute(o, "attr")).thenReturn("va");

            assertFalse(matcher.match(o, reader));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            NodeReadWriter reader = mock(NodeReadWriter.class);

            when(reader.getAttribute(o, "attr")).thenReturn("vals");

            assertFalse(matcher.match(o, reader));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            NodeReadWriter reader = mock(NodeReadWriter.class);

            when(reader.getAttribute(o, "attr1")).thenReturn("val");

            assertFalse(matcher.match(o, reader));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            NodeReadWriter reader = mock(NodeReadWriter.class);

            when(reader.getAttribute(o, "att")).thenReturn("val");

            assertFalse(matcher.match(o, reader));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            NodeReadWriter reader = mock(NodeReadWriter.class);

            when(reader.getAttribute(o, "attr")).thenReturn("val");
            when(reader.getAttribute(o, "attr2")).thenReturn("val");

            assertTrue(matcher.match(o, reader));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            Object o = new Object();
            NodeReadWriter reader = mock(NodeReadWriter.class);

            when(reader.getAttribute(o, "attr")).thenReturn("val2");
            when(reader.getAttribute(o, "attr2")).thenReturn("val");

            assertFalse(matcher.match(o, reader));
        }
    }

}
