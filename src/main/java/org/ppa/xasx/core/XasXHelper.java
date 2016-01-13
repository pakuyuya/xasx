package org.ppa.xasx.core;

public class XasXHelper {
    static public XasXException wrapException(Throwable e) {
        return new XasXException(e);
    }
}
