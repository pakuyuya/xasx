package org.ppa.xmlvalidator.util;

public class ValueCache<T> {

    public ValueCache(){ };
    public ValueCache(T value){ this.value = value; };

    private T value;

    public T get() {
        return value;
    }
    public void set(T value) {
        this.value = value;
    }
}
