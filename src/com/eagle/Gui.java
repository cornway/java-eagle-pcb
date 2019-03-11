package com.eagle;

import org.apache.commons.collections4.bag.CollectionBag;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Роман on 13.06.2017.
 */
public class Gui extends JFrame {
    private JButton buttonSelectSpreadsheet;
    private JButton buttonGenerate;
    private JButton buttonSelectDirectory;
    private JTextArea textAreaReport;
    private JCheckBox checkBoxAddPinPositionToPinName;
    private JCheckBox checkBoxSplitSymbolByType;
    private JTextField textFieldControl;
    private JPanel rootPanel;
    private JCheckBox checkBoxPrefix;

    private static StringBuilder stringBuilder;


    public Gui ()
    {
        super("Eagle Lib. Converter");
        super.setContentPane(rootPanel);
        super.setSize(new Dimension(400, 300));
        super.setVisible(true);
        super.pack();
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textAreaReport.setText("");
        textAreaReport.setEditable(false);
        checkBoxAddPinPositionToPinName.setSelected(false);
        checkBoxSplitSymbolByType.setSelected(false);

        textFieldControl.setText("7.7.0");

        JFileChooser fileChooser = new JFileChooser();
        JFileChooser  directoryChooser = new JFileChooser();

        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f == null) {
                    return false;
                }
                if (f.isDirectory()) {
                    return true;
                }
                String[] s = f.getName().split("[.]");
                if (s.length == 2) {
                    if (s[1].equalsIgnoreCase("xlsx")) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String getDescription() {
                return ".xlsx";
            }
        });
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        Gui gui = this;

        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(buttonSelectSpreadsheet);
        buttons.add(buttonSelectDirectory);
        buttons.add(buttonGenerate);

        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(checkBoxAddPinPositionToPinName);
        checkBoxes.add(checkBoxPrefix);
        checkBoxes.add(checkBoxSplitSymbolByType);

        buttonSelectSpreadsheet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonSelectSpreadsheet) {
                    if (fileChooser.showOpenDialog(gui) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        Eagle.spreadsheetPath = file.toPath().toString();
                        Eagle.libName = file.getName().split("[.]")[0];
                        Eagle.libPath = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
                        System.out.println(Eagle.libPath);
                    }
                }
            }
        });

        buttonSelectDirectory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonSelectDirectory) {
                    if (directoryChooser.showOpenDialog(gui) == JFileChooser.APPROVE_OPTION) {
                        File file = directoryChooser.getSelectedFile();
                        Eagle.libPath = file.toPath().toString();
                    }
                }
            }
        });

        buttonGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonGenerate) {
                    Eagle.deviceSymbolSplitByType = checkBoxSplitSymbolByType.isSelected();
                    Eagle.isPinPositionPrefix = checkBoxPrefix.isSelected();
                    Eagle.pinPositionToName = checkBoxAddPinPositionToPinName.isSelected();
                    Eagle.version = textFieldControl.getText();
                    Thread thread = new Thread(() -> {
                        buttons.forEach(button -> button.setEnabled(false));
                        checkBoxes.forEach(checkBox -> checkBox.setEnabled(false));


                        stringBuilder = new StringBuilder();
                        try {
                            new Eagle();
                            textAreaReport.setText(stringBuilder.toString());
                        } catch (FileNotFoundException ex) {
                            textAreaReport.setText("File Process Error ! : " + ex.toString());
                        } catch (IOException ioEx) {
                            textAreaReport.setText("File Process Error ! : " + ioEx.toString());
                        }

                        buttons.forEach(button -> button.setEnabled(true));
                        checkBoxes.forEach(checkBox -> checkBox.setEnabled(true));
                    });
                    thread.start();
                }
            }
        });
    }

    public static void main (String[] args)
    {
        new Gui();
    }

    public static <T>void appendText (T t)
    {
        stringBuilder.append(t);
    }

}
