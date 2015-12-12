package org.ppa.xmlvalidator.core.rulefileparser;

import java.io.File;

import org.ppa.xmlvalidator.core.validate.ValidateNode;

/**
 * 検証ルールファイルをValidateNodeに変換するパーサ
 */
public interface RulefileParser {
    ValidateNode parse(File file);
}
