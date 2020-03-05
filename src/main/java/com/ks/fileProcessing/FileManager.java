package com.ks.fileProcessing;

import com.ks.model.Column;
import com.ks.model.DataType;
import com.mifmif.common.regex.Generex;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {
    private static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String numbers = "0123456789";
    private static final String alpha = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static List<Column> columns = new LinkedList<>();

    private Timer timer = new javax.swing.Timer(30000, (e) -> System.gc());

    public void start(Integer fieSizeInMB, String fileURL) {
        timer.start();

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileURL), StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            while ((new File(fileURL).length() / 1024) < fieSizeInMB * 1024) {
                bw.write(columns.stream().map(this::fillValues).collect(Collectors.joining()));
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error while file is created by: " + e.getMessage() + " origin: " + Arrays.toString(e.getStackTrace()));
        }

        System.out.println("File created successfully");
    }

    private String fillValues(Column column) {
        StringBuilder val = new StringBuilder();
        boolean forced = false;

        while ((val.length() < column.getLength() && !forced || column.getLength() == 0)) {
            switch (column.getType()) {
                case INTEGER:
                    val.append(numbers.charAt(getRandomInt(0, 9)));
                    break;
                case BOOLEAN_STRING:
                    val.append(Math.random() < 0.5);
                    forced = true;
                    break;
                case BOOLEAN_INTEGER:
                    val.append(Math.random() < 0.5 ? 1 : 0);
                    forced = true;
                    break;
                case STRING:
                    val.append(column.getDefaultValue() != null && !column.getDefaultValue().isEmpty() ? column.getDefaultValue() : chars.charAt(getRandomInt(0, 51)));
                    break;
                case DATE:
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DataType.DATE.getFormat());
                    val.append(simpleDateFormat.format(new Date()));
                    break;
                case TIME:
                    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(DataType.TIME.getFormat());
                    val.append(simpleTimeFormat.format(new Date()));
                    break;
                default:
                    System.out.println("data type for fill values not defined");
                    return "";
            }
        }

        try {
            return column.format(val.toString());
        } catch (Exception e) {
            System.out.println("Fatal fill value " + e.getMessage());
            System.exit(-1);
            return "";
        }
    }


    private int getRandomInt(int min, int max) {
        return (int) ((Math.random() * max) + 1);
    }
}
