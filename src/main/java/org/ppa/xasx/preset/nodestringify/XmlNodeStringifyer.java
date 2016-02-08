package org.ppa.xasx.preset.nodestringify;

import java.util.Collection;
import java.util.stream.Collectors;

import org.ppa.xasx.core.NodeDefine;
import org.ppa.xasx.core.ValueNodeProxy;
import org.ppa.xasx.core.matcher.Matcher;
import org.ppa.xasx.core.validate.ValidateContext;
import org.ppa.xasx.preset.valuemaker.SimpleValueMaker;
import org.ppa.xasx.simple.SimpleNode;
import org.ppa.xasx.simple.SimpleNodeReadWriter;
import org.ppa.xasx.types.NodeStringifyer;




import static org.ppa.xasx.util.XasXStringUtil.*;

import org.ppa.xasx.util.XasXXmlUtil;

public class XmlNodeStringifyer implements NodeStringifyer {

    @Override
    public String convert(Collection<NodeDefine> defines, ValidateContext context) {
        return defines.stream()
            .map(define -> convert(define, context))
            .collect(Collectors.joining());
    }

    @Override
    public String convert(NodeDefine define, ValidateContext context) {
        ValueNodeProxy node = new ValueNodeProxy(new SimpleNode(), new SimpleNodeReadWriter(), new SimpleValueMaker());

        for (Matcher matcher : define.getMatchers()) {
            matcher.mapToNode(node, context);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        sb.append(isNotEmpty(node.getName()) ? node.getName() : context.getWildcardDisplay());

        for (String attrName : node.getAttributeNames()) {
            String attrValue = node.getAttribute(attrName);
            sb.append(' ');
            sb.append(attrName);

            sb.append("=\"");
            sb.append(attrValue != null ? XasXXmlUtil.encodeXmlValue(attrValue) : context.getWildcardDisplay());
            sb.append('"');
        }

        sb.append(">");

        return sb.toString();
    }

}
