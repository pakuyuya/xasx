package org.ppa.xasx.core.rulefileparser;

import java.util.HashMap;
import java.util.Map;

import org.ppa.xasx.core.NodeDefine;

public class Configs {
    private NodeDefine rootNode;
    private Map<String, String> props = new HashMap<String, String>();

    public NodeDefine getRootNode() {
        return rootNode;
    }
    public void setRootNode(NodeDefine rootNode) {
        this.rootNode = rootNode;
    }
    public Map<String, String> getProps() {
        return props;
    }
    public void setProps(Map<String, String> props) {
        this.props = props;
    }
}
