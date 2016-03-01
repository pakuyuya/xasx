package org.ppa.xasx.core.rulefileparser;

import static org.ppa.xasx.util.XasXLangUtil.*;
import static org.ppa.xasx.util.XasXStringBeanUtil.*;
import static org.ppa.xasx.util.XasXStringUtil.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.XasXException;
import org.ppa.xasx.core.matcher.MatcherHelper;
import org.ppa.xasx.core.validate.ValidateContext;
import org.ppa.xasx.types.ParameterCheckable;
import org.ppa.xasx.types.Rule;
import org.ppa.xasx.types.Translate;
import org.ppa.xasx.util.XasXXmlUtil;
import org.ppa.xasx.util.XmlElementData;
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
            XmlElementData rulefile = XasXXmlUtil.parseHierarchical(file);
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
                        ret.setValidRoots(convertValidation(elm, localTranslateClassMap, localRuleClassMap, localProperties, defaultTranslats));
                        break;
                    }
                }
            }

            validateConfig(ret);
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

        String rulePrefix = properties.get(ConfigsPropKey.RULE_PREFIX);
        if (isEmpty(rulePrefix)) {
            rulePrefix = "rule:";
        }

        String transPrefix = properties.get(ConfigsPropKey.TRANSLATE_PREFIX);
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
                        throw new RuntimeException("rule '" + ruleName + "' is not defined.");
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
                        throw new RuntimeException("translate " + transName + " is not defined.");
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
            XmlElementData settings = XasXXmlUtil.parseHierarchical(file);
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

                if (elm.getName().equals("translate")) {
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

    private void validateConfig(Configs config) {
        ValidateContext context = new ValidateContext();
        List<Exception> errors = new ArrayList<Exception>();
        validateDefine(config.getValidRoot(), context, errors);
    }

    private void validateDefine(NodeDefine node, ValidateContext context, List<Exception> errors) {
        context.getValidateStack().push(node);
        checkParameters(node.getRules(), context, errors);
        checkParameters(node.getTranslats(), context, errors);
        for (NodeDefine child : node.getChildren()) {
            validateDefine(child, context, errors);
        }
        context.getValidateStack().pop();
    }

    private <T> void checkParameters(Collection<T> targets, ValidateContext context, List<Exception> errors) {
        for (T target : targets) {
            if (target instanceof ParameterCheckable) {
                try {
                    ((ParameterCheckable)target).checkParameter(context);
                } catch (XasXException ex) {
                    errors.add(ex);
                }
            }
        }
    }
}
