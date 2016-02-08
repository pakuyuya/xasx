package org.ppa.xasx;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class XmlValidatorTest {

    private final String CLASSPATH_BASE = "org/ppa/xasx/testXmlValidator/";

    @Test
    public void test001Standard(){
        Map<String, List<String>> errors1 = getErrors("test001/data001.xml", "test001/rule001.xml");
        assertTrue(errors1.isEmpty());
    }

    private File getFileFromClasspath(String filepath) {
        final String path = getClass().getClassLoader().getResource(filepath).getPath();
        return new File(path);
    }

    private Map<String, List<String>> getErrors(String xmlFile, String ruleFile) {
        return getErrors(
                getFileFromClasspath(CLASSPATH_BASE + xmlFile)
                ,getFileFromClasspath(CLASSPATH_BASE + ruleFile)
            );
    }

    private Map<String, List<String>> getErrors(File xmlFile, File ruleFile) {
        return (new XmlValidator()).validate(xmlFile, ruleFile);
    }
}
