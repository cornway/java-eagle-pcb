package com.eagle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Роман on 11.06.2017.
 */
public class LbrCreator {

    public LbrCreator (DeviceDescriptor deviceDescriptor) throws IOException
    {
        File file = new File(Eagle.libPath + File.separator + Eagle.libName + "." + Eagle.libExtension);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        if (Eagle.DEBUG) {
            System.out.println("File Path : " + file.getPath());
            System.out.println(file.exists());
        }
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath(), Eagle.charset,
                StandardOpenOption.APPEND, StandardOpenOption.WRITE)) {

            writeOpenTag(bufferedWriter, EagleLbrBook.XML_DOCTYPE);
            writeOpenTag(bufferedWriter, EagleLbrBook.DOCTYPE);

            Map<String, String> map = new LinkedHashMap<>();
            map.put(EagleLbrBook.VERSION_TAG_NAME, Eagle.version);
            writeOpenTag(bufferedWriter, EagleLbrBook.EAGLE_TAG_NAME, map);
            writeOpenTag(bufferedWriter, EagleLbrBook.DRAWING_TAG_NAME);

            writeSettings(bufferedWriter);
            writeGrid(bufferedWriter);
            writeDefaultLayersInfo(bufferedWriter);

            writeOpenTag(bufferedWriter, EagleLbrBook.LIBRARY_TAG_NAME);

            writeDeviceSymbol(bufferedWriter, deviceDescriptor.getSymbolDescriptor());

            if (Eagle.writeConnects) {
                writeOpenTag(bufferedWriter, EagleLbrBook.PACKAGES_TAG_NAME);
                map.put(EagleLbrBook.NAME_TAG_NAME, deviceDescriptor.getPackageName());
                writeOpenTag(bufferedWriter, EagleLbrBook.PACKAGE_TAG_NAME, map);
                writeCloseTag(bufferedWriter, EagleLbrBook.PACKAGE_TAG_NAME);
                writeCloseTag(bufferedWriter, EagleLbrBook.PACKAGES_TAG_NAME);

                writeDeviceDescriptor(bufferedWriter, deviceDescriptor);
            }

            writeCloseTag(bufferedWriter, EagleLbrBook.LIBRARY_TAG_NAME);
            writeCloseTag(bufferedWriter, EagleLbrBook.DRAWING_TAG_NAME);
            writeCloseTag(bufferedWriter, EagleLbrBook.EAGLE_TAG_NAME);

            bufferedWriter.close();
            if (Eagle.DEBUG) {
                System.out.println("File Write successful !");
            }
            Gui.appendText("File Process Successful !" + EagleLbrBook.NEW_LINE);
        }
    }


    private void writeDefaultLayersInfo (BufferedWriter bufferedWriter) throws IOException
    {
        writeOpenTag(bufferedWriter, EagleLbrBook.LAYERS_TAG_NAME);
        bufferedWriter.write(EagleLbrBook.DEFAULT_LAYER_INFO);
        writeCloseTag(bufferedWriter, EagleLbrBook.LAYERS_TAG_NAME);
    }

    private void writeSettings (BufferedWriter bufferedWriter) throws IOException
    {
        writeOpenTag(bufferedWriter, EagleLbrBook.SETTINGS_TAG_NAME);
        Map<String, String> map = new LinkedHashMap<>();
        map.put(EagleLbrBook.ALWAYS_VECTOR_FONT_TAG_NAME, Eagle.ALWAYS_VECTOR_FONT);
        writeElement(bufferedWriter, EagleLbrBook.SETTING_TAG_NAME, map);
        map.put(EagleLbrBook.VERTICAL_TEXT_TAG_NAME, Eagle.VERTICAL_TEXT);
        writeElement(bufferedWriter, EagleLbrBook.SETTING_TAG_NAME, map);
        writeCloseTag(bufferedWriter, EagleLbrBook.SETTINGS_TAG_NAME);
    }

    private void writeGrid (BufferedWriter bufferedWriter) throws IOException
    {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(EagleLbrBook.DISTANCE_TAG_NAME, Eagle.DEFAULT_DISTANCE_INCHES);
        map.put(EagleLbrBook.UNITDISTANCE_TAG_NAME, Eagle.DEFAULT_METRIC);
        map.put(EagleLbrBook.UNIT_TAG_NAME, Eagle.DEFAULT_METRIC);
        map.put(EagleLbrBook.STYLE_TAG_NAME, Eagle.DEFAULT_STYLE);
        map.put(EagleLbrBook.MULTIPLE_TAG_NAME, Eagle.DEFAULT_MULTIPLE);
        map.put(EagleLbrBook.DISPLAY_TAG_NAME, Eagle.DEFAULT_DISPLAY);
        map.put(EagleLbrBook.ALTDISTANCE_TAG_NAME, Eagle.DEFAULT_ALT_DISTANCE_INCHES);
        map.put(EagleLbrBook.ALTUNITDISTANCE_TAG_NAME, Eagle.DEFAULT_METRIC);
        map.put(EagleLbrBook.ALTUNIT_TAG_NAME, Eagle.DEFAULT_METRIC);
        writeElement(bufferedWriter, EagleLbrBook.GRID_TAG_NAME, map);
    }

    private void writeDeviceDescriptor (BufferedWriter bufferedWriter, DeviceDescriptor deviceDescriptor) throws IOException
    {

        writeOpenTag(bufferedWriter, EagleLbrBook.DEVICESETS_TAG_NAME);
        Map<String, String> map = new LinkedHashMap<>();
        map.put(EagleLbrBook.NAME_TAG_NAME, deviceDescriptor.getSymbolDescriptor().getName());
        writeOpenTag(bufferedWriter, EagleLbrBook.DEVICESET_TAG_NAME, map);
        writeOpenTag(bufferedWriter, EagleLbrBook.GATES_TAG_NAME);
        if (Eagle.deviceSymbolSplitByType) {
            Map<String, ArrayList<Pin>> pinsMap = deviceDescriptor.getSymbolDescriptor().getSortByType();
            int i = 1;
            double step = Eagle.gridStep;
            for (String typeName : pinsMap.keySet()) {
                map.put(EagleLbrBook.NAME_TAG_NAME, Eagle.UNNAMED_GATE_NAME_PREFIX + Integer.toString(i));
                map.put(EagleLbrBook.SYMBOL_TAG_NAME, deviceDescriptor.getSymbolDescriptor().getName() + "_" + typeName);
                map.put("x", "0" + Double.toString(i * 32 * step));
                map.put("y", "0");
                writeElement(bufferedWriter, EagleLbrBook.GATE_TAG_NAME, map);
                i++;
            }
        } else {
            map.put(EagleLbrBook.NAME_TAG_NAME, Eagle.UNNAMED_GATE_NAME_PREFIX + "1");
            map.put(EagleLbrBook.SYMBOL_TAG_NAME, deviceDescriptor.getSymbolDescriptor().getName());
            map.put("x", "0");
            map.put("y", "0");
            writeElement(bufferedWriter, EagleLbrBook.GATE_TAG_NAME, map);
        }


        writeCloseTag(bufferedWriter, EagleLbrBook.GATES_TAG_NAME);

        writeOpenTag(bufferedWriter, EagleLbrBook.DEVICES_TAG_NAME);

        map.put(EagleLbrBook.NAME_TAG_NAME, "");
        map.put(EagleLbrBook.PACKAGE_TAG_NAME, deviceDescriptor.getPackageName());
        writeOpenTag(bufferedWriter, EagleLbrBook.DEVICE_TAG_NAME, map);

        writeOpenTag(bufferedWriter, EagleLbrBook.CONNECTS_TAG_NAME);
        if (Eagle.deviceSymbolSplitByType) {
            Map<String, ArrayList<Pin>> pinsMap = deviceDescriptor.getSymbolDescriptor().getSortByType();
            int i = 1;
            for (String typeName : pinsMap.keySet()) {
                for (Pin pin : pinsMap.get(typeName)) {
                    map.put(EagleLbrBook.GATE_TAG_NAME, Eagle.UNNAMED_GATE_NAME_PREFIX + Integer.toString(i));
                    map.put(EagleLbrBook.PIN_TAG_NAME, pin.getName());
                    map.put(EagleLbrBook.PAD_TAG_NAME, Eagle.UNNAMED_PIN_NAME_PREFIX + pin.getPosition());
                    writeElement(bufferedWriter, EagleLbrBook.CONNECT_TAG_NAME, map);
                }
                i++;
            }
        } else {
            for (Pin pin : deviceDescriptor.getSymbolDescriptor().getPins()) {
                map.put(EagleLbrBook.GATE_TAG_NAME, Eagle.UNNAMED_GATE_NAME_PREFIX + "1");
                map.put(EagleLbrBook.PIN_TAG_NAME, pin.getName());
                map.put(EagleLbrBook.PAD_TAG_NAME, Eagle.UNNAMED_PIN_NAME_PREFIX + pin.getPosition());
                writeElement(bufferedWriter, EagleLbrBook.CONNECT_TAG_NAME, map);
            }
        }
        writeCloseTag(bufferedWriter, EagleLbrBook.CONNECTS_TAG_NAME);

        writeOpenTag(bufferedWriter, EagleLbrBook.TECHNOLOGIES_TAG_NAME);
        map.put(EagleLbrBook.NAME_TAG_NAME, "");
        writeElement(bufferedWriter, EagleLbrBook.TECHNOLOGY_TAG_NAME, map);
        writeCloseTag(bufferedWriter, EagleLbrBook.TECHNOLOGIES_TAG_NAME);

        writeCloseTag(bufferedWriter, EagleLbrBook.DEVICE_TAG_NAME);
        writeCloseTag(bufferedWriter, EagleLbrBook.DEVICES_TAG_NAME);


        writeCloseTag(bufferedWriter, EagleLbrBook.DEVICESET_TAG_NAME);
        writeCloseTag(bufferedWriter, EagleLbrBook.DEVICESETS_TAG_NAME);
    }

    private void writeDeviceSymbol (BufferedWriter bufferedWriter, SymbolDescriptor symbolDescriptor) throws IOException
    {
        writeOpenTag(bufferedWriter, EagleLbrBook.SYMBOLS_TAG_NAME);
        if (Eagle.deviceSymbolSplitByType) {
           Map<String, ArrayList<Pin>> map = symbolDescriptor.getSortByType();
           ArrayList<Pin> array;
           for (String typeName : map.keySet()) {
               array = map.get(typeName);
               Pin[] pins = new Pin[array.size()];
               array.toArray(pins);
               writeSymbol(bufferedWriter, symbolDescriptor.getName() + "_" + typeName, pins);
           }
        } else {
            writeSymbol(bufferedWriter, symbolDescriptor.getName(), symbolDescriptor.getPins());
        }
        writeCloseTag(bufferedWriter, EagleLbrBook.SYMBOLS_TAG_NAME);
    }

    private void writeSymbol (BufferedWriter bufferedWriter, String name, Pin[] pins) throws IOException
    {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(EagleLbrBook.NAME_TAG_NAME, name);
        writeOpenTag(bufferedWriter, EagleLbrBook.SYMBOL_TAG_NAME, map);

        /*write rectangle*/
        float step = Eagle.gridStep;
        float step2 = step * 2.0f;
        float height = pins.length * step;
        float middleHeight;
        if (pins.length % 2 == 0) {
            /*pin count is even*/
            middleHeight = height / 2;
        } else {
            middleHeight = ((pins.length + 1) / 2) * step;
        }

        writeSymbolRectangle(
                bufferedWriter,
                - step * 12,
                height / 2,
                step * 24,
                height + step,
                EagleLbrBook.SYMBOL_WIRE_WIDTH,
                EagleLbrBook.SYMBOL_WIRE_LAYER
        );

        /*write left-sided pins*/
        for (int i = 0; i < pins.length / 2; i++) {
            writeSymbolPin(
                    bufferedWriter,
                    pins[i].getName(),
                    -step * 16,
                    middleHeight - step2 * i - step,
                    Eagle.DEFAULT_PIN_LENGTH,
                    EagleLbrBook.ROT0
            );
        }
        /*write right-sided pins*/
        for (int i = pins.length / 2; i < pins.length; i++) {
            writeSymbolPin(
                    bufferedWriter,
                    pins[i].getName(),
                    step * 16,
                    step2 * i - height - middleHeight + step,
                    Eagle.DEFAULT_PIN_LENGTH,
                    EagleLbrBook.ROT180
            );
        }

        writeCloseTag(bufferedWriter, EagleLbrBook.SYMBOL_TAG_NAME);
    }

    private void writeSymbolWire (BufferedWriter bufferedWriter, float x1, float y1, float x2, float y2, float width, int layer) throws IOException
    {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("x1", Float.toString(x1));
        map.put("y1", Float.toString(y1));
        map.put("x2", Float.toString(x2));
        map.put("y2", Float.toString(y2));
        map.put(EagleLbrBook.WIDTH_TAG_NAME, Float.toString(width));
        map.put(EagleLbrBook.LAYER_TAG_NAME, Integer.toString(layer));
        writeElement(bufferedWriter, EagleLbrBook.WIRE_TAG_NAME, map);
    }

    private void writeSymbolPin (BufferedWriter  bufferedWriter, String name, float x1, float y1, String length, String rot) throws IOException
    {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(EagleLbrBook.NAME_TAG_NAME, name);
        map.put("x", Float.toString(x1));
        map.put("y", Float.toString(y1));
        map.put(EagleLbrBook.LENGTH_TAG_NAME, length);
        map.put(EagleLbrBook.ROTATION_TAG_NAME, rot);
        writeElement(bufferedWriter, EagleLbrBook.PIN_TAG_NAME, map);
    }

    private void writeSymbolRectangle (BufferedWriter bufferedWriter, float x1, float y1, float w, float h, float wireWidth, int layer) throws IOException
    {
        writeSymbolWire(bufferedWriter, x1, y1, x1 + w, y1, wireWidth, layer); /*top line*/
        writeSymbolWire(bufferedWriter, x1 + w, y1, x1 + w, y1 - h, wireWidth, layer);/*right line*/
        writeSymbolWire(bufferedWriter, x1, y1 - h, x1 + w, y1 - h, wireWidth, layer); /*bottom line*/
        writeSymbolWire(bufferedWriter, x1, y1, x1, y1 - h, wireWidth, layer); /*left line*/
    }

    private void writeOpenTag (BufferedWriter bufferedWriter, String name) throws IOException
    {
        bufferedWriter.write(EagleLbrBook.OPEN_BRACE + name + EagleLbrBook.CLOSE_BRACE + EagleLbrBook.NEW_LINE);
    }

    private <K, V>void writeOpenTag (BufferedWriter bufferedWriter, String name, Map<K, V> vars) throws IOException
    {
        bufferedWriter.write(EagleLbrBook.OPEN_BRACE + name);
        Set<K> set = vars.keySet();
        for (K key : set) {
            bufferedWriter.write(" " + key.toString() + "=" + "\"" + vars.get(key).toString() + "\"");
        }
        vars.clear();
        bufferedWriter.write(EagleLbrBook.CLOSE_BRACE + EagleLbrBook.NEW_LINE);
    }

    private void writeCloseTag (BufferedWriter bufferedWriter, String name) throws IOException
    {
        bufferedWriter.write(EagleLbrBook.OPEN_BRACE + "/" + name + EagleLbrBook.CLOSE_BRACE + EagleLbrBook.NEW_LINE);
    }

    private <K, V>void writeElement (BufferedWriter bufferedWriter, String name, Map<K, V> vars) throws IOException
    {
        bufferedWriter.write(EagleLbrBook.OPEN_BRACE + name);
        Set<K> set = vars.keySet();
        for (K key : set) {
            bufferedWriter.write(" " + key.toString() + "=" + "\"" + vars.get(key).toString() + "\"");
        }
        vars.clear();
        bufferedWriter.write("/" + EagleLbrBook.CLOSE_BRACE + EagleLbrBook.NEW_LINE);
    }

}
