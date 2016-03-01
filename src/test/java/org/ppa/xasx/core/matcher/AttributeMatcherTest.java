package org.ppa.xasx.core.matcher;

import static org.junit.Assert.*;

import org.junit.Test;
import org.ppa.xasx.core.ValueNodeProxy;
import org.ppa.xasx.simple.SimpleNode;
import org.ppa.xasx.simple.SimpleNodeReadWriter;
import org.ppa.xasx.types.NodeReadWriter;

public class AttributeMatcherTest {

    @Test
    public void standardTest() {
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            SimpleNode o = new SimpleNode();
            NodeReadWriter reader = new SimpleNodeReadWriter();
            ValueNodeProxy node = new ValueNodeProxy(o, reader, null);

            o.getAttributes().put("attr", "val");
            assertTrue(matcher.match(node));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            SimpleNode o = new SimpleNode();
            NodeReadWriter reader = new SimpleNodeReadWriter();
            ValueNodeProxy node = new ValueNodeProxy(o, reader, null);

            assertFalse(matcher.match(node));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            SimpleNode o = new SimpleNode();
            NodeReadWriter reader = new SimpleNodeReadWriter();
            ValueNodeProxy node = new ValueNodeProxy(o, reader, null);

            o.getAttributes().put("attr", "vals");

            assertFalse(matcher.match(node));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            SimpleNode o = new SimpleNode();
            NodeReadWriter reader = new SimpleNodeReadWriter();
            ValueNodeProxy node = new ValueNodeProxy(o, reader, null);

            o.getAttributes().put("attr1", "val");

            assertFalse(matcher.match(node));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            SimpleNode o = new SimpleNode();
            NodeReadWriter reader = new SimpleNodeReadWriter();
            ValueNodeProxy node = new ValueNodeProxy(o, reader, null);

            o.getAttributes().put("att", "val");

            assertFalse(matcher.match(node));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            SimpleNode o = new SimpleNode();
            NodeReadWriter reader = new SimpleNodeReadWriter();
            ValueNodeProxy node = new ValueNodeProxy(o, reader, null);

            o.getAttributes().put("attr", "val");
            o.getAttributes().put("attr2", "val");

            assertTrue(matcher.match(node));
        }
        {
            AttributeMatcher matcher = new AttributeMatcher("attr", "val");
            SimpleNode o = new SimpleNode();
            NodeReadWriter reader = new SimpleNodeReadWriter();
            ValueNodeProxy node = new ValueNodeProxy(o, reader, null);

            o.getAttributes().put("attr", "val2");
            o.getAttributes().put("attr2", "val");

            assertFalse(matcher.match(node));
        }
    }

}
