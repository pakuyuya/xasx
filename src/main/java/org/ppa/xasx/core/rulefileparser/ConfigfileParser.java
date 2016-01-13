package org.ppa.xasx.core.rulefileparser;

import java.io.File;

/**
 * 検証ルールファイルをValidateNodeに変換するパーサ
 */
public interface ConfigfileParser {
    Configs parse(File file);
}
