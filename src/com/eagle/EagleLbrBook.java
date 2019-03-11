package com.eagle;

/**
 * Created by Роман on 11.06.2017.
 */
public class EagleLbrBook {
    public static final char OPEN_BRACE = '<';
    public static final char CLOSE_BRACE = '>';
    public static final String NEW_LINE = "\r\n";

    public static final String XML_DOCTYPE = "?xml version=\"1.0\" encoding=\"utf-8\"?";
    public static final String DOCTYPE = "!DOCTYPE eagle SYSTEM \"eagle.dtd\"";

    public static final float SYMBOL_WIRE_WIDTH = 0.254f;
    public static final int SYMBOL_WIRE_LAYER = 94;
    public static final String VERSION_TAG_NAME = "version";
    public static final String EAGLE_TAG_NAME = "eagle";
    public static final String DRAWING_TAG_NAME = "drawing";
    public static final String LIBRARY_TAG_NAME = "library";
    public static final String PACKAGES_TAG_NAME = "packages";
    public static final String PACKAGE_TAG_NAME = "package";
    public static final String NAME_TAG_NAME = "name";
    public static final String LAYERS_TAG_NAME = "layers";
    public static final String LAYER_TAG_NAME = "layer";
    public static final String SETTINGS_TAG_NAME = "settings";
    public static final String SETTING_TAG_NAME = "setting";
    public static final String ALWAYS_VECTOR_FONT_TAG_NAME = "alwaysvectorfont";
    public static final String VERTICAL_TEXT_TAG_NAME = "verticaltext";
    public static final String GRID_TAG_NAME = "grid";
    public static final String DISTANCE_TAG_NAME = "distance";
    public static final String UNITDISTANCE_TAG_NAME = "unitdist";
    public static final String UNIT_TAG_NAME = "unit";
    public static final String STYLE_TAG_NAME = "style";
    public static final String MULTIPLE_TAG_NAME = "multiple";
    public static final String DISPLAY_TAG_NAME = "display";
    public static final String ALTDISTANCE_TAG_NAME = "altdistance";
    public static final String ALTUNITDISTANCE_TAG_NAME = "altunitdist";
    public static final String ALTUNIT_TAG_NAME = "altunit";
    public static final String DEVICESETS_TAG_NAME = "devicesets";
    public static final String DEVICESET_TAG_NAME = "deviceset";
    public static final String GATES_TAG_NAME = "gates";
    public static final String SYMBOLS_TAG_NAME = "symbols";
    public static final String SYMBOL_TAG_NAME = "symbol";
    public static final String DEVICES_TAG_NAME = "devices";
    public static final String DEVICE_TAG_NAME = "device";
    public static final String CONNECTS_TAG_NAME = "connects";
    public static final String CONNECT_TAG_NAME = "connect";
    public static final String GATE_TAG_NAME = "gate";
    public static final String PIN_TAG_NAME = "pin";
    public static final String PAD_TAG_NAME = "pad";
    public static final String ROTATION_TAG_NAME = "rot";
    public static final String LENGTH_TAG_NAME = "length";
    public static final String WIDTH_TAG_NAME = "width";
    public static final String WIRE_TAG_NAME = "wire";
    public static final String TECHNOLOGIES_TAG_NAME = "technologies";
    public static final String TECHNOLOGY_TAG_NAME = "technology";


    public static final String ROT0 = "R0";
    public static final String ROT180 = "R180";


    public static final String DEFAULT_LAYER_INFO =
            "<layer number=\"1\" name=\"Top\" color=\"4\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"16\" name=\"Bottom\" color=\"1\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"17\" name=\"Pads\" color=\"2\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"18\" name=\"Vias\" color=\"2\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"19\" name=\"Unrouted\" color=\"6\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"20\" name=\"Dimension\" color=\"15\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"21\" name=\"tPlace\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"22\" name=\"bPlace\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"23\" name=\"tOrigins\" color=\"15\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"24\" name=\"bOrigins\" color=\"15\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"25\" name=\"tNames\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"26\" name=\"bNames\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"27\" name=\"tValues\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"28\" name=\"bValues\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"29\" name=\"tStop\" color=\"7\" fill=\"3\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"30\" name=\"bStop\" color=\"7\" fill=\"6\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"31\" name=\"tCream\" color=\"7\" fill=\"4\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"32\" name=\"bCream\" color=\"7\" fill=\"5\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"33\" name=\"tFinish\" color=\"6\" fill=\"3\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"34\" name=\"bFinish\" color=\"6\" fill=\"6\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"35\" name=\"tGlue\" color=\"7\" fill=\"4\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"36\" name=\"bGlue\" color=\"7\" fill=\"5\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"37\" name=\"tTest\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"38\" name=\"bTest\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"39\" name=\"tKeepout\" color=\"4\" fill=\"11\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"40\" name=\"bKeepout\" color=\"1\" fill=\"11\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"41\" name=\"tRestrict\" color=\"4\" fill=\"10\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"42\" name=\"bRestrict\" color=\"1\" fill=\"10\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"43\" name=\"vRestrict\" color=\"2\" fill=\"10\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"44\" name=\"Drills\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"45\" name=\"Holes\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"46\" name=\"Milling\" color=\"3\" fill=\"1\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"47\" name=\"Measures\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"48\" name=\"Document\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"49\" name=\"Reference\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"51\" name=\"tDocu\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"52\" name=\"bDocu\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"90\" name=\"Modules\" color=\"5\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"91\" name=\"Nets\" color=\"2\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"92\" name=\"Busses\" color=\"1\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"93\" name=\"Pins\" color=\"2\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"94\" name=\"Symbols\" color=\"4\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"95\" name=\"Names\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"96\" name=\"Values\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"97\" name=\"Info\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE +
            "<layer number=\"98\" name=\"Guide\" color=\"6\" fill=\"1\" visible=\"yes\" active=\"yes\"/>" + NEW_LINE;


    private EagleLbrBook ()
    {

    }

}
