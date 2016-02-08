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
import org.ppa.xasx.core.rulefileparser.ConfigfileParser;
import org.ppa.xasx.core.rulefileparser.Configs;
import org.ppa.xasx.core.rulefileparser.ConfigsPropKey;
import org.ppa.xasx.core.rulefileparser.XMLConfigfileParser;
import org.ppa.xasx.core.validate.ValidateContext;
import org.ppa.xasx.core.validate.ValidateEngine;
import org.ppa.xasx.core.validate.ValidateEngineHelper;
import org.ppa.xasx.preset.message.FormatMessageResolver;
import org.ppa.xasx.preset.message.MapMessageStock;
import org.ppa.xasx.preset.nodestringify.XmlNodeStringifyer;
import org.ppa.xasx.preset.valuemaker.MergeStringChildrenValueMaker;
import org.ppa.xasx.xml.XmlNodeReadWriter;
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

        props.put(ConfigsPropKey.DEFAULT_VALUE_NODE_READWRITER, XmlNodeReadWriter.class.getName());
        props.put(ConfigsPropKey.DEFAULT_VALUE_MAKER, MergeStringChildrenValueMaker.class.getName());
        props.put(ConfigsPropKey.MESSAGE_RESOLVER, FormatMessageResolver.class.getName());
        props.put(ConfigsPropKey.NODE_STRINGIFYER, XmlNodeStringifyer.class.getName());
        props.put(ConfigsPropKey.WILDCARD_DISPLAY, "*");

        props.putAll(configs.getProps()); // 設定ファイルの値でデフォルト値を上書き

        // contextの初期化
        ValidateContext validationContext = new ValidateContext();
        ValidateEngineHelper.setupValidationContext(validationContext, props);
        ValueIOContext valueIOContext = new ValueIOContext();
        ValidateEngineHelper.setupValueReadContext(valueIOContext, props);

        // エラースタックの初期化
        MapMessageStock errors = new MapMessageStock();

        try {
            // validation実施
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(xmlFile);
            Node node = document.getDocumentElement();

            validateEngine.validateRecursive(node, configs.getValidRoots(), valueIOContext, validationContext, errors);

            return errors.getMessages();

        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw XasXHelper.wrapException(e);
        }

    }
}
