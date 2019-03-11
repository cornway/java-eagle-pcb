package com.eagle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Роман on 12.06.2017.
 */
public class SymbolDescriptor {
    private ArrayList<Pin> pins;
    private String name;

    public SymbolDescriptor (String name)
    {
        pins = new ArrayList<>();
        this.name = name;
    }

    public void addPin (Pin pin)
    {
        pins.add(pin);
    }

    public Pin[] getPins ()
    {
        Pin[] pins1 = new Pin[pins.size()];
        int i = 0;
        for (Pin pin :  pins) {
            pins1[i++] = pin;
        }
        return pins1;
    }

    public String getName ()
    {
        return name;
    }

    public Map<String, ArrayList<Pin>> getSortByType ()
    {
        Map<String, ArrayList<Pin>> map = new HashMap<>();
        ArrayList<Pin> arrayList;
        for (Pin p : pins) {
            arrayList = map.get(p.getType());
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                arrayList.add(p);
                map.put(p.getType(), arrayList);
            } else {
                arrayList.add(p);
            }
        }
        return map;
    }


    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Symbol name : " + name + "\r\n");
        for (Pin p : pins) {
            sb.append(p.toString() + "\r\n");
        }
        return sb.toString();
    }

}
