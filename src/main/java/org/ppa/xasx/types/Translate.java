package org.ppa.xasx.types;

import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.validate.ValidateContext;

/**
 * 値変換処理
 */
public interface Translate {
    ValueNode translate(final ValueNode node, ValidateContext validContext);
}
