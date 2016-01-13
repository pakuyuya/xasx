package org.ppa.xasx;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ppa.xasx.core.ValueIOContext;
import org.ppa.xasx.core.XasXHelper;
import org.ppa.xasx.core.message.MapMessageStock;
import org.ppa.xasx.core.rulefileparser.ConfigfileParser;
import org.ppa.xasx.core.rulefileparser.Configs;
import org.ppa.xasx.core.rulefileparser.ConfigsPropKey;
import org.ppa.xasx.core.rulefileparser.XMLConfigfileParser;
import org.ppa.xasx.core.validate.ValidateContext;
import org.ppa.xasx.core.validate.ValidateEngine;
import org.ppa.xasx.core.validate.ValidateEngineHelper;
import org.ppa.xasx.core.valuemaker.MergeStringChildrenValueMaker;
import org.ppa.xasx.xml.XmlNodeValueNodeReader;
import org.ppa.xasx.xml.XmlNodeValueNodeWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class XmlValidator {

    private ValidateEngine validateEngine = new ValidateEngine();
    private ConfigfileParser ruleFilePerser = new XMLConfigfileParser();

    public Map<String, List<String>> validate(File xmlFile, File ruleFile){
        Configs configs = ruleFilePerser.parse(ruleFile);


        // プロパティの初期化
        Map<String, String> props = new HashMap<String, String>();

        props.put(ConfigsPropKey.DEFAULT_VALUE_NODE_READER, XmlNodeValueNodeReader.class.getName());
        props.put(ConfigsPropKey.DEFAULT_VALUE_NODE_WRITER, XmlNodeValueNodeWriter.class.getName());
        props.put(ConfigsPropKey.DEFAULT_VALUE_MAKER, MergeStringChildrenValueMaker.class.getName());

        props.putAll(configs.getProps()); // 設定ファイルの値でデフォルト値を上書き

        // contextの初期化
        ValidateContext validationContext = new ValidateContext();
        ValidateEngineHelper.setupValidationContext(validationContext, configs.getProps());
        ValueIOContext valueReadContext = new ValueIOContext();
        ValidateEngineHelper.setupValueReadContext(valueReadContext, configs.getProps());

        // エラースタックの初期化
        MapMessageStock errors = new MapMessageStock();

        try {
            // validation実施
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(xmlFile);
            Node node = document.getDocumentElement();

            validateEngine.validateRecursive(node, configs.getRootNode(), valueReadContext, validationContext, errors);

            return errors.getMessages();

        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw XasXHelper.wrapException(e);
        }

    }
}
