package org.ppa.xasx.preset.message;

import java.util.ArrayList;
import java.util.List;

import org.ppa.xasx.core.message.MessageStock;

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
