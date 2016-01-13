package org.ppa.xmlvalidator.core.rulefileparser;

import static org.ppa.xmlvalidator.util.XmlValidatorLangUtil.*;
import static org.ppa.xmlvalidator.util.XmlValidatorStringBeanUtil.*;
import static org.ppa.xmlvalidator.util.XmlValidatorStringUtil.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.ppa.xmlvalidator.core.Rule;
import org.ppa.xmlvalidator.core.Translate;
import org.ppa.xmlvalidator.core.NodeDefine;
import org.ppa.xmlvalidator.core.matcher.MatcherHelper;
import org.ppa.xmlvalidator.util.XmlElementData;
import org.ppa.xmlvalidator.util.XmlValidatorXmlUtil;
import org.xml.sax.SAXException;


/**
 * XML形式設定ファイルパーサ
 */
public class XMLConfigfileParser implements ConfigfileParser {

    Map<String, String> translateClassMap = new HashMap<String, String>();
    Map<String, String> ruleClassMap = new HashMap<String, String>();
    Map<String, String> properties = new HashMap<String, String>();

    List<String> defaultTranslats = new ArrayList<String>();

    /**
     * XML検証ルールファイルをparseする
     * @param file 検証ルールファイル
     * @return 検証ルール
     */
    public Configs parse(File file) {
        Configs ret = new Configs();

        try {
            XmlElementData rulefile = XmlValidatorXmlUtil.parseHierarchical(file);
            Map<String, String> localTranslateClassMap = new HashMap<String, String>();
            localTranslateClassMap.putAll(translateClassMap);
            Map<String, String> localRuleClassMap = new HashMap<String, String>();
            localRuleClassMap.putAll(ruleClassMap);
            Map<String, String> localProperties = new HashMap<String, String>();
            localProperties.putAll(properties);
            List<String> localDefaultTranslats = new ArrayList<String>();
            localDefaultTranslats.addAll(defaultTranslats);

            for (Object child : rulefile.getChildren()) {
                if (child instanceof XmlElementData) {
                    XmlElementData elm = cast(child);

                    String name = elm.getName();
                    switch(name) {
                    case "settings":
                        loadSettings(elm, localTranslateClassMap, localRuleClassMap, localProperties, defaultTranslats);
                        break;
                    case "validation":
                        ret.setRootNode(convertValidation(elm, localTranslateClassMap, localRuleClassMap, localProperties, defaultTranslats));
                        break;
                    }
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    private NodeDefine convertValidation(XmlElementData validation,
            Map<String, String> translateClassMap, Map<String, String> ruleClassMap,
            Map<String, String> properties, List<String> defaultTranslats) {

        NodeDefine node = new NodeDefine();

        node.getMatchers().addAll(MatcherHelper.createMatchers(validation));

        String rulePrefix = properties.get("rule-prefix");
        if (isEmpty(rulePrefix)) {
            rulePrefix = "rule:";
        }

        String transPrefix = properties.get("trans-prefix");
        if (isEmpty(transPrefix)) {
            transPrefix = "trans:";
        }

        for (Object o : validation.getChildren()) {
            if (o instanceof XmlElementData) {
                XmlElementData child = cast(o);

                String name = child.getName();
                if (name.startsWith(rulePrefix)) {
                    String ruleName = name.substring(rulePrefix.length());
                    if (!ruleClassMap.containsKey(ruleName)){
                        throw new RuntimeException("rule " + ruleName + " is not defined.");
                    }

                    try {
                        Class<?> clazz = Class.forName(ruleClassMap.get(ruleName));
                        Rule rule = cast(clazz.newInstance());
                        for (Entry<String, String> entry : child.getAttributes().entrySet()) {
                            setStringProperty(rule, entry.getKey(), entry.getValue());
                        }
                        node.getRules().add(rule);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        new RuntimeException(e);
                    }
                } else if (name.startsWith(transPrefix)) {
                    String transName = name.substring(transPrefix.length());
                    if (!translateClassMap.containsKey(transName)){
                        throw new RuntimeException("rule " + transName + " is not defined.");
                    }

                    try {
                        Class<?> clazz = Class.forName(translateClassMap.get(transName));
                        Translate trans = cast(clazz.newInstance());

                        for (Entry<String, String> entry : child.getAttributes().entrySet()) {
                            setStringProperty(trans, entry.getKey(), entry.getValue());
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        new RuntimeException(e);
                    }
                } else {
                    NodeDefine childNode = convertValidation(child, translateClassMap, ruleClassMap, properties, defaultTranslats);
                    node.getChildren().add(childNode);
                }
            }
        }

        return node;
    }

    /**
     * 設定ファイルを読み込む
     * @param file
     */
    public void loadSettingFile(File file) {
        try {
            XmlElementData settings = XmlValidatorXmlUtil.parseHierarchical(file);
            loadSettings(settings, translateClassMap, ruleClassMap, properties, defaultTranslats);
        } catch (SAXException | IOException | ParserConfigurationException e ) {
            throw new RuntimeException(e);
        }
    }

    private void loadSettings(XmlElementData settings,
                              Map<String, String> translateClassMap, Map<String, String> ruleClassMap,
                              Map<String, String> properties, List<String> defaultTranslats) {
        for (Object child : settings.getChildren()) {
            if (child instanceof XmlElementData) {
                XmlElementData elm = cast(child);

                String name = elm.getName();
                switch(name) {
                case "rules":
                    loadSettingRules(elm, ruleClassMap);
                    break;
                case "translats":
                    loadSettingTranslats(elm, translateClassMap);
                    break;
                case "defaultTranslats":
                    loadSettingDefaultTranslats(elm, defaultTranslats);
                    break;
                case "prop":
                    loadSettingProp(elm, properties);
                    break;
                }
            }
        }
    }

    private void loadSettingRules(XmlElementData rules, Map<String, String> ruleClassMap) {
        for (Object child : rules.getChildren()) {
            if (child instanceof XmlElementData) {
                XmlElementData elm = cast(child);

                if (elm.getName().equals("rule")) {
                    if (!elm.getAttributes().containsKey("name") || !elm.getAttributes().containsKey("class")) {
                        continue;
                    }
                    Map<String, String> attributes = elm.getAttributes();
                    ruleClassMap.put(attributes.get("name"), attributes.get("class"));
                }
            }
        }
    }

    private void loadSettingTranslats(XmlElementData translats, Map<String, String> translateClassMap) {
        for (Object child : translats.getChildren()) {
            if (child instanceof XmlElementData) {
                XmlElementData elm = cast(child);

                if (elm.getName().equals("transfer")) {
                    if (!elm.getAttributes().containsKey("name") || !elm.getAttributes().containsKey("class")) {
                        continue;
                    }
                    Map<String, String> attributes = elm.getAttributes();
                    translateClassMap.put(attributes.get("name"), attributes.get("class"));
                }
            }
        }
    }

    private void loadSettingDefaultTranslats(XmlElementData translats, List<String> properties) {
        for (Object child : translats.getChildren()) {
            if (child instanceof String) {
                String content = (String)child;

                Arrays.asList(content.split(",")).stream()
                    .filter(c -> isNotBlankAscii(c))
                    .forEach(c -> properties.add(c));
            }
        }
    }


    private void loadSettingProp(XmlElementData prop, Map<String, String> properties) {
        if (!prop.getAttributes().containsKey("name")) {
            return;
        }
        String name = prop.getAttributes().get("name");

        // 最初のTextNodeを値とする。ない場合は空文字
        String value = "";

        for (Object child : prop.getChildren()) {
            if (child instanceof String) {
                value = cast(child);
                break;
            }
        }

        properties.put(name, value);
    }
}
