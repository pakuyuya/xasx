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
    public void validateRecursive(final Object root, final NodeDefine rootDefines, ValueIOContext valueIOContext, ValidateContext validateContext, MessageStock errors) {

        for (NodeDefine rootDefine : rootDefines.getChildren()) {
            ValueNode node = createNode(root, rootDefine, valueIOContext);

            if (isMatchNode(node, rootDefine.getMatchers(), valueIOContext)) {
                validateRecurciveInner(node, rootDefine, valueIOContext, validateContext, errors);
            }
        }
    }

    private void validateRecurciveInner(final ValueNode node, final NodeDefine nodeDefine, ValueIOContext valueIOContext, ValidateContext validateContext, MessageStock errors) {
        validateContext.getValidateStack().push(nodeDefine);

        for (Rule rule : nodeDefine.getRules()) {
            ErrorMessage error = rule.validateNode(node, nodeDefine, validateContext);
            if (error != null)
                errors.push(error.getName(), error.getMessage());
        }

        validateChildren(node, nodeDefine, valueIOContext, validateContext, errors);

        validateContext.getValidateStack().pop();
    }

    private void validateChildren(final ValueNode node, final NodeDefine nodeDefine, ValueIOContext valueIOContext, ValidateContext validateContext, MessageStock errors) {
        List<Object> children = new ArrayList<Object>(node.getChildrenCount());
        for (int i=0, len=node.getChildrenCount(); i<len; i++) {
            children.add(node.getChild(i));
        }

        List<NodeDefine> childDefines = nodeDefine.getChildren();

        boolean matchedValidNodes[] = new boolean[nodeDefine.getChildren().size()];
        for (int i=0; i<matchedValidNodes.length; i++) {
            matchedValidNodes[i] = false;
        }

        for (Object child : children) {
            validchildLoop:
            for (int i = 0; i<childDefines.size(); i++) {
                NodeDefine childDefine = childDefines.get(i);
                ValueIOContext childValueIOCtx = cast(cloneInstance(valueIOContext));

                ValueNode childNode = createNode(child, childDefine, childValueIOCtx);
                if (!isMatchNode(childNode, childDefine.getMatchers(), childValueIOCtx)) {
                    continue validchildLoop;
                }
                ValidateContext childValidateCtx = cast(cloneInstance(validateContext));
                validateRecurciveInner(childNode, childDefine, childValueIOCtx, childValidateCtx, errors);

                matchedValidNodes[i] = true;
            }
        }

        for (int i=0; i<matchedValidNodes.length; i++) {
            if (!matchedValidNodes[i]) {
                ValidateContext childValidateCtx = cast(cloneInstance(validateContext));
                terminateUnmatchedNodeDefine(childDefines.get(i), childValidateCtx, errors);
            }
        }

        for (NodeDefine validChild : nodeDefine.getChildren()) {
            for (Rule rule : validChild.getRules()) {
                ErrorMessage error = rule.onLeaveScope(validChild, validateContext);
                if (error != null)
                    errors.push(error.getName(), error.getMessage());
            }
        }
    }

    private void terminateUnmatchedNodeDefine(final NodeDefine nodeDefine, final ValidateContext context, MessageStock errors) {
        context.getValidateStack().push(nodeDefine);
        for (NodeDefine child : nodeDefine.getChildren()) {
            for (Rule rule : child.getRules()) {
                ErrorMessage error = rule.onLeaveScope(nodeDefine, context);
                if (error != null)
                    errors.push(error.getName(), error.getMessage());
            }
            for (NodeDefine childNode :nodeDefine.getChildren()) {
                terminateUnmatchedNodeDefine(childNode, context, errors);
            }
        }
        context.getValidateStack().pop();
    }

    private boolean isMatchNode(ValueNode node, List<Matcher> matchers, ValueIOContext valueIOContext){
        for (Matcher matcher : matchers) {
            if (!matcher.match(node)) {
                return false;
            }
        }
        return true;
    }

    private NodeReadWriter resolveValueNodeReader(NodeDefine nodeDefine, ValueIOContext valueIOContext) {
        return (nodeDefine.getValueNodeReader() != null)
                    ? nodeDefine.getValueNodeReader() : valueIOContext.getDefaultNodeReadWriter();
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
