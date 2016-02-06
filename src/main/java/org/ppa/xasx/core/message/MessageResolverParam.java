package org.ppa.xasx.core.message;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageResolverのパラメータ
 * @see MessageResolver
 */
public class MessageResolverParam {
    private String template;
    private List<Object> params = new ArrayList<>();

    /**
     * @return template
     */
    public String getTemplate() {
        return template;
    }
    /**
     * @param template セットする template
     */
    public void setTemplate(String template) {
        this.template = template;
    }
    /**
     * @return params
     */
    public List<Object> getParams() {
        return params;
    }
    /**
     * @param params セットする params
     */
    public void setParams(List<Object> params) {
        this.params = params;
    }

    public void addParam(Object ... values) {
        for (Object value : values){
            params.add(value);
        }
    }
}
