package com.eagle;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by Роман on 11.06.2017.
 */
public class DeviceDescriptor {
    private SymbolDescriptor symbolDescriptor;
    private String packageName;

    public DeviceDescriptor (String pathToExcel, String symbolName, String packageName) throws FileNotFoundException, IOException
    {
        symbolDescriptor = new SymbolDescriptor(symbolName);
        this.packageName = packageName;
        File file = new File(pathToExcel);
        if (file.exists() == false) {
            if (Eagle.DEBUG) {
                System.out.println("File : [" + pathToExcel + "] not found !");
            }
            throw new FileNotFoundException("");
        }
        if (Eagle.DEBUG) {
            System.out.println("File : [" + pathToExcel + "]");
        }
        Gui.appendText("Selected File : " + file.getPath() + EagleLbrBook.NEW_LINE);
        Gui.appendText("Symbol name : " + symbolName + EagleLbrBook.NEW_LINE);

        FileInputStream excelFile = new FileInputStream(new File(pathToExcel));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Gui.appendText("File Read Successful !");

        int rowCount = 0;
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            processRow(symbolDescriptor, currentRow.iterator());
            rowCount++;
        }
        Gui.appendText("Rows Processed : " + Integer.toString(rowCount) + EagleLbrBook.NEW_LINE);
            /**/
        if (Eagle.DEBUG) {
            System.out.println("symbol description  : ");
            System.out.println(symbolDescriptor.toString());

            System.out.println("\n\n\nDevice description sorted by type : ");
            Map<String, ArrayList<Pin>> map = symbolDescriptor.getSortByType();
            for (String name : map.keySet()) {
                System.out.println("Type : [" + name + "]");
                for (Pin p : map.get(name)) {
                    System.out.println(p.toString());
                }
            }
        }
        Gui.appendText("Port Types : " + EagleLbrBook.NEW_LINE);
        for (String typeName : symbolDescriptor.getSortByType().keySet()) {
            Gui.appendText("[" + typeName + "], ");
        }
        Gui.appendText(EagleLbrBook.NEW_LINE);
    }

    private void processRow (SymbolDescriptor symbolDescriptor, Iterator<Cell> iterator)
    {
        Cell cell;
        String pins = "";
        String pin = "";
        String type = "";
        String name = "";
        String attribute = "";
        /*first will be a pin position*/
        if (iterator.hasNext()) {
            cell = iterator.next();

            if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                /*only one pin present*/
                pin = Integer.toString((int)cell.getNumericCellValue());
            } else if (cell.getCellTypeEnum() == CellType.STRING) {
                /*pins set are present*/
                pins = cell.getStringCellValue();
            } else {
                /*not a valid data present*/
                return;
            }
        } else {
            return;
        }
        if (iterator.hasNext()) {
            cell = iterator.next();
            if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                /*must be a pin name not a value*/
                return;
            } else if (cell.getCellTypeEnum() == CellType.STRING) {
                name = cell.getStringCellValue();
                if (Eagle.pinPositionToName) {
                    if (pin.length() > 0) {
                        if (Eagle.isPinPositionPrefix) {
                            name = pin + "_" + name;
                        } else {
                            name += "_" + pin;
                        }
                    }
                }
            } else {
                return;
            }
        } else {
            return;
        }

        if (iterator.hasNext()) {
             cell = iterator.next();
             if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                 /*must be a pin type not an integer value*/
                 return;
             } else if (cell.getCellTypeEnum() == CellType.STRING) {
                 type = cell.getStringCellValue();
             } else {
                 return;
             }
        } else {
            return;
        }

        while (iterator.hasNext()) {
            /*has an additional information*/
            /*all other data will append to attribute string*/
            cell = iterator.next();
            if (cell.getCellTypeEnum() == CellType.NUMERIC) {

            } else if (cell.getCellTypeEnum() == CellType.STRING) {
                attribute += cell.getStringCellValue();
            } else {

            }
        }

        if (pins.length() > 0) {
            pins = pins.replaceAll("\\s+", "");
        } else {
            pin = pin.replaceAll("\\s+", "");
        }

        name = name.replaceAll("\\s+", "");
        type = type.replaceAll("\\s+", "");
        attribute = attribute.replaceAll("\\s+", "");

        if (pins.length() > 0) {
            /*collect all pins from one row*/
            String[] pinsArray = pins.split("[,]");
            String[] namesArray = name.split("[,]");
            Map<String, String> pinMap = new HashMap<>();
            if (pinsArray.length == namesArray.length) {
                for (int i = 0; i < pinsArray.length; i++) {
                    pinMap.put(namesArray[i], pinsArray[i]);
                }
            } else {
                for (int i = 0; i < pinsArray.length; i++) {
                    pinMap.put(name + "_" + Integer.toString(i), pinsArray[i]);
                }
            }
            Set<String> pinsSet = pinMap.keySet();
            for (String p : pinsSet) {
                if (Eagle.pinPositionToName) {
                    if (Eagle.isPinPositionPrefix) {
                        symbolDescriptor.addPin(new Pin(pinMap.get(p), pinMap.get(p) + "_" + p, type, attribute));
                    } else {
                        symbolDescriptor.addPin(new Pin(pinMap.get(p), p + "_" + pinMap.get(p), type, attribute));
                    }
                } else {
                    symbolDescriptor.addPin(new Pin(pinMap.get(p), p, type, attribute));
                }
            }
        } else {
            /*only one pin present*/
            symbolDescriptor.addPin(new Pin(pin, name, type, attribute));
        }

        System.out.println();
    }

    public SymbolDescriptor getSymbolDescriptor ()
    {
        return symbolDescriptor;
    }

    public String getPackageName ()
    {
        return packageName;
    }
}
