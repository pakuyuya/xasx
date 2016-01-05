package org.ppa.xmlvalidator.core.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMessageStock implements MessageStock {
    private Map<String, List<String>> messages = new HashMap<String, List<String>>();

    public Map<String, List<String>> getMessages() {
        return messages;
    }

    @Override
    public void push(String name, String message) {
        if (!messages.containsKey(name)) {
            messages.put(name, new ArrayList<String>());
        }
        messages.get(name).add(message);
    }
}
