package com.eagle;

/**
 * Created by Роман on 12.06.2017.
 */
public class Pin {

    private String type;
    private String name;
    private String position;
    private String attribute;

    public Pin (String position, String name, String type, String attribute)
    {
        this.position = position;
        this.name = name;
        this.type = type;
        this.attribute = attribute;
    }

    public Pin (String position, String name, String type)
    {
        this.position = position;
        this.name = name;
        this.type = type;
        this.attribute = "";
    }


    public String getName ()
    {
        return name;
    }

    public String getType ()
    {
        return type;
    }

    public String getPosition ()
    {
        return position;
    }

    public String getAttribute ()
    {
        return attribute;
    }


    public String toString ()
    {
        return "Pin : [" + name + "], position : [" + position + "], type : [" + type + "], attributes : [" + attribute + "];";
    }
}
