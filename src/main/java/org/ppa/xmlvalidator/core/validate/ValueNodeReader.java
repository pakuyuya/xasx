package org.ppa.xmlvalidator.core.validate;

import org.w3c.dom.Node;


/**
 * 検証対象のオブジェクトから、ValueNodeを作る。
 *
 * @param <T> 検証対象の型
 */
public interface ValueNodeReader<T> {

    public ValueNode convertNode(String name, Node src, ValueReadContext context);
}
