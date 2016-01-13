package org.ppa.xmlvalidator.core;

public class XasXHelper {
    static public XasXException wrapException(Throwable e) {
        return new XasXException(e);
    }
}
