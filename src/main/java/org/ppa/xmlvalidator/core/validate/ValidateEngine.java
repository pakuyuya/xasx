package org.ppa.xmlvalidator.core.validate;

import static org.ppa.xmlvalidator.util.XmlValidatorLangUtil.*;

import java.util.ArrayList;
import java.util.List;

import org.ppa.xmlvalidator.core.ErrorMessage;
import org.ppa.xmlvalidator.core.NodeDefine;
import org.ppa.xmlvalidator.core.Rule;
import org.ppa.xmlvalidator.core.ValueIOContext;
import org.ppa.xmlvalidator.core.ValueNode;
import org.ppa.xmlvalidator.core.ValueNodeProxy;
import org.ppa.xmlvalidator.core.ValueNodeReader;
import org.ppa.xmlvalidator.core.ValueNodeWriter;
import org.ppa.xmlvalidator.core.matcher.Matcher;
import org.ppa.xmlvalidator.core.message.MessageStock;

/**
 * 検証のメインロジック
 */
public class ValidateEngine {

    /**
     * 与えられたValueNodeに対し、ValidateNodeによる検証を行います。
     * @param valueNode
     * @param validateNode
     * @return
     */
    public void validateRecursive(final Object root, final NodeDefine nodeDefine, ValueIOContext valueIOContext, ValidateContext validateContext, MessageStock errors) {

        ValueNode node = createNode(root, nodeDefine, valueIOContext);
        if (isMatchNode(node, nodeDefine.getMatchers(), valueIOContext)) {
            validateRecurciveInner(node, nodeDefine, valueIOContext, validateContext, errors);
        }
    }

    private void validateRecurciveInner(final ValueNode node, final NodeDefine nodeDefine, ValueIOContext valueIOContext, ValidateContext validateContext, MessageStock errors) {
        validateContext.getValidateStack().push(nodeDefine);

        for (Rule rule : nodeDefine.getRules()) {
            ErrorMessage error = rule.validateNode(node, nodeDefine, validateContext);
            if (error != null)
                errors.push(error.getName(), error.getMessage());
        }

        List<Object> children = new ArrayList<Object>(node.getChildrenCount(valueIOContext));
        for (int i=0, len=node.getChildrenCount(valueIOContext); i<len; i++) {
            children.add(node.getChild(i, valueIOContext));
        }

        for (Object child : children) {
            validchildLoop:
            for (NodeDefine childDefine : nodeDefine.getChildren()) {
                ValueIOContext childValueIOCtx = cast(cloneInstance(valueIOContext));
                childValueIOCtx.setValueNodeReader(childDefine.getValueNodeReader() != null ? childDefine.getValueNodeReader() : childValueIOCtx.getDefaultValueNodeReader());
                childValueIOCtx.setValueNodeWriter(childDefine.getValueNodeWriter() != null ? childDefine.getValueNodeWriter() : childValueIOCtx.getDefaultValueNodeWriter());

                for (Matcher matcher : childDefine.getMatchers()) {
                    if (!matcher.match(child, childValueIOCtx)) {
                        continue validchildLoop;
                    }
                }
                ValidateContext childValidateCtx = cast(cloneInstance(validateContext));
                ValueNode childNode = createNode(child, childDefine, childValueIOCtx);
                validateRecurciveInner(childNode, childDefine, childValueIOCtx, childValidateCtx, errors);
            }
        }

        for (NodeDefine validChild : nodeDefine.getChildren()) {
            for (Rule rules : validChild.getRules()) {
                ErrorMessage error = rules.onLeaveScope(validChild, validateContext);
                if (error != null)
                    errors.push(error.getName(), error.getMessage());
            }
        }

        validateContext.getValidateStack().pop();
    }

    private boolean isMatchNode(ValueNode node, List<Matcher> matchers, ValueIOContext valueIOContext){
        for (Matcher matcher : matchers) {
            if (!matcher.match(node, valueIOContext)) {
                return false;
            }
        }
        return true;
    }

    private ValueNodeReader resolveValueNodeReader(NodeDefine nodeDefine, ValueIOContext valueIOContext) {
        if (nodeDefine.getValueNodeReader() != null) {
            return nodeDefine.getValueNodeReader();
        }
        return valueIOContext.getDefaultValueNodeReader();
    }

    private ValueNodeWriter resolveValueNodeWriter(NodeDefine nodeDefine, ValueIOContext valueIOContext) {
        if (nodeDefine.getValueNodeWriter() != null) {
            return nodeDefine.getValueNodeWriter();
        }
        return valueIOContext.getDefaultValueNodeWriter();
    }

    private ValueNode createNode(Object node, NodeDefine nodeDefine, ValueIOContext context){
        ValueNodeWriter writer = resolveValueNodeWriter(nodeDefine, context);
        ValueNodeReader reader = resolveValueNodeReader(nodeDefine, context);
        return new ValueNodeProxy(node, reader, writer);
    }
}
