package org.ppa.xmlvalidator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlValidatorCommonUtil {
    static public <KEY, VAL> void putMapList(Map<KEY, List<VAL> > map, KEY key, VAL value){
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<VAL>());
        }
        map.get(key).add(value);
    }
}
