package org.ppa.xasx.core;

public class XasXException extends RuntimeException {
    public XasXException(String s) {
        super(s);
    }
    public XasXException(Throwable e) {
        super(e);
    }
}
