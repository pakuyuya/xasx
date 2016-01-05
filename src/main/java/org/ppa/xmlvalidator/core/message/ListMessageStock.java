package org.ppa.xmlvalidator.core.message;

import java.util.ArrayList;
import java.util.List;

public class ListMessageStock implements MessageStock {
    private List<String> messages = new ArrayList<String>();

    public List<String> getMessages() {
        return messages;
    }

    @Override
    public void push(String name, String message) {
        messages.add(message);
    }
}
