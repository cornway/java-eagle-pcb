package com.eagle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by Роман on 11.06.2017.
 */
public class Eagle {
    public static final boolean DEBUG = true;

    public static final String ALWAYS_VECTOR_FONT = "yes";
    public static final String VERTICAL_TEXT = "up";
    public static final String DEFAULT_DISTANCE_INCHES = "0.1";
    public static final String DEFAULT_ALT_DISTANCE_INCHES = "0.01";
    public static final String DEFAULT_METRIC = "inch";
    public static final String DEFAULT_STYLE = "lines";
    public static final String DEFAULT_MULTIPLE = "1";
    public static final String DEFAULT_DISPLAY = "no";
    public static final String DEFAULT_PIN_LENGTH = "middle";


    public static final String UNNAMED_GATE_NAME_PREFIX = "G$";
    public static final String UNNAMED_PIN_NAME_PREFIX = "P$";

    public static final float gridStep = 1.27f;
    public static final String libExtension = "lbr";

    public static String version;
    public static String libPath;
    public static String libName;
    public static String packageName;
    public static String spreadsheetPath;
    public static Charset charset;
    public static boolean deviceSymbolSplitByType;
    public static boolean pinPositionToName;
    public static boolean isPinPositionPrefix;
    public static boolean writeConnects;

    static {
        version = "7.7.0";
        libPath = "";
        libName = "misc";
        packageName = "";
        spreadsheetPath = "spreadsheet.xlsx";
        charset = StandardCharsets.UTF_8;
        deviceSymbolSplitByType = true;
        pinPositionToName = true;
        isPinPositionPrefix = true;
        writeConnects = false;
    }

    public Eagle () throws FileNotFoundException, IOException
    {
        if (DEBUG) {
            System.out.println("version : " + version);
            System.out.println("libPath : " + libPath);
            System.out.println("libName : " + libName);
            System.out.println("packageName : " + packageName);
            System.out.println("spreadsheetPath : " + spreadsheetPath);
        }
        new LbrCreator( new DeviceDescriptor(spreadsheetPath, libName, packageName) );
    }
}
