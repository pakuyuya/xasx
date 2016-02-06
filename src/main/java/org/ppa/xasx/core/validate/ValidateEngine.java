package org.ppa.xasx.core.validate;

import static org.ppa.xasx.util.XasXLangUtil.*;

import java.util.ArrayList;
import java.util.List;

import org.ppa.xasx.core.ErrorMessage;
import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.ValueIOContext;
import org.ppa.xasx.core.ValueNode;
import org.ppa.xasx.core.ValueNodeProxy;
import org.ppa.xasx.core.matcher.Matcher;
import org.ppa.xasx.core.message.MessageStock;
import org.ppa.xasx.types.Rule;
import org.ppa.xasx.types.ValueMaker;
import org.ppa.xasx.types.NodeReadWriter;

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

        List<Object> children = new ArrayList<Object>(node.getChildrenCount());
        for (int i=0, len=node.getChildrenCount(); i<len; i++) {
            children.add(node.getChild(i));
        }

        for (Object child : children) {
            validchildLoop:
            for (NodeDefine childDefine : nodeDefine.getChildren()) {
                ValueIOContext childValueIOCtx = cast(cloneInstance(valueIOContext));
                childValueIOCtx.setValueNodeReaderWriter(childDefine.getValueNodeReader() != null ? childDefine.getValueNodeReader() : childValueIOCtx.getDefaultValueNodeReader());

                for (Matcher matcher : childDefine.getMatchers()) {
                    if (!matcher.match(child, childValueIOCtx.getValueNodeReaderWriter())) {
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
            if (!matcher.match(node, valueIOContext.getValueNodeReaderWriter())) {
                return false;
            }
        }
        return true;
    }

    private NodeReadWriter resolveValueNodeReader(NodeDefine nodeDefine, ValueIOContext valueIOContext) {
        return (nodeDefine.getValueNodeReader() != null)
                    ? nodeDefine.getValueNodeReader() : valueIOContext.getDefaultValueNodeReader();
    }

    private ValueMaker resolveValueMaker(NodeDefine nodeDefine, ValueIOContext valueIOContext) {
        return (nodeDefine.getValueMaker() != null)
                ? nodeDefine.getValueMaker() : valueIOContext.getDefaultValueMaker();
    }

    private ValueNode createNode(Object node, NodeDefine nodeDefine, ValueIOContext context){
        NodeReadWriter readWriter = resolveValueNodeReader(nodeDefine, context);
        ValueMaker valueMaker = resolveValueMaker(nodeDefine, context);
        return new ValueNodeProxy(node, readWriter, valueMaker);
    }
}
