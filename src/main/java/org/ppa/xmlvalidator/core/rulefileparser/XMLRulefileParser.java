package org.ppa.xmlvalidator.core.rulefileparser;

import static org.ppa.xmlvalidator.util.XmlVlidatorLangUtil.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.ppa.xmlvalidator.core.validate.ValidateNode;
import org.ppa.xmlvalidator.util.XmlElementData;
import org.ppa.xmlvalidator.util.XmlUtil;
import org.xml.sax.SAXException;


/**
 * XML検証ルールファイルのパーサ
 */
public class XMLRulefileParser implements RulefileParser {

    Map<String, String> translateClassMap = new HashMap<String, String>();
    Map<String, String> ruleClassMap = new HashMap<String, String>();
    Map<String, String> properties = new HashMap<String, String>();

    List<String> defaultTranslats = new ArrayList<String>();

    /**
     * XML検証ルールファイルをparseする
     * @param file 検証ルールファイル
     * @return 検証ルール
     */
    public ValidateNode parse(File file) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /**
     * 設定ファイルを読み込む
     * @param file
     */
    public void loadSettingFile(File file) {
        try {
            XmlElementData settings = XmlUtil.parseHierarchical(file);

            for (Object child : settings.getChildren()) {
                if (child instanceof XmlElementData) {
                    XmlElementData elm = cast(child);

                    String name = elm.getName();
                    if (name.equals("rules")) {
                        loadSettingRules(elm);
                    } else if (name.equals("translats")) {
                        loadSettingTranslats(elm);
                    } else if (name.equals("defaultTranslats")) {
                        loadSettingDefaultTranslats(elm);
                    } else if (name.equals("prop")) {
                        loadSettingProp(elm);
                    }
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e ) {
            throw new RuntimeException(e);
        }
    }

    private void loadSettingRules(XmlElementData rules) {
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

    private void loadSettingTranslats(XmlElementData translats) {
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

    private void loadSettingDefaultTranslats(XmlElementData translats) {
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

    private void loadSettingProp(XmlElementData prop) {
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
