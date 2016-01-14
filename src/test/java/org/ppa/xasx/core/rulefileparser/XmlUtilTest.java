package org.ppa.xasx.core.rulefileparser;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.ppa.xasx.util.XmlElementData;
import org.ppa.xasx.util.XasXXmlUtil;
import org.xml.sax.SAXException;

public class XmlUtilTest {

    @Test
    public void parseHierachicalTest() throws SAXException, IOException, ParserConfigurationException {
        XmlElementData settings = XasXXmlUtil.parseHierarchical(this.getClass().getClassLoader()
                    .getResourceAsStream("util/XmlElementData/parse1.xml"));

        assertEquals(settings.getName(), "settings");
        assertEquals(settings.getChildren().size(), 2);

        XmlElementData rules = (XmlElementData)settings.getChildren().get(0);

        assertEquals(rules.getChildren().size(), 3);

        String rulesNames[] = { "require", "requireuc", "minmaxlen"};
        String rulesClass[] = {
                "org.ppa.xmlvalidator.ruleset.RequireRule",
                "org.ppa.xmlvalidator.ruleset.RequireUCRule",
                "org.ppa.xmlvalidator.ruleset.MinmaxlenRule"
            };

        for (int i = 0; i < rules.getChildren().size(); i++) {

            assertTrue(rules.getChildren().get(i) instanceof XmlElementData);

            XmlElementData child = (XmlElementData) rules.getChildren().get(i);
            assertEquals(rulesNames[i], child.getAttributes().get("name"));
            assertEquals(rulesClass[i], child.getAttributes().get("class"));
        }

        XmlElementData prop = (XmlElementData) settings.getChildren().get(1);

        assertEquals("prop" ,prop.getName());

        assertEquals(1, prop.getChildren().size());

        assertEquals("rule:", prop.getChildren().get(0));
    }

}
