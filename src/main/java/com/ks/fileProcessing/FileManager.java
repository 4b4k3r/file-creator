package com.ks.fileProcessing;

import com.ks.model.Columns;
import com.ks.model.DataType;

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
import java.util.stream.Collectors;

public class FileManager
{
    private static final String chars = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "0123456789";

    private Timer timer = new javax.swing.Timer(30000, (e) -> System.gc());

    public void start(Integer fieSizeInMB, String fileURL)
    {
        timer.start();

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileURL), StandardCharsets.UTF_8, StandardOpenOption.CREATE))
        {
            while ((new File(fileURL).length() / 1024) < fieSizeInMB * 1024)
            {
                bw.write(Arrays.stream(Columns.values()).parallel().map(this::fillValues).collect(Collectors.joining()));
                bw.newLine();
            }
        }
        catch (Exception e)
        {
            System.out.println("Error while file is created by: " + e.getMessage() + " origin: " + Arrays.toString(e.getStackTrace()));
        }

        System.out.println("File created successfully");
    }

    private String fillValues(Columns column)
    {
        StringBuilder val = new StringBuilder();

        while (val.length() < column.getLength())
        {
            switch (column.getDataType())
            {
                case INTEGER:
                    val.append(numbers.charAt(getRandomInt(0, 9)));
                    break;
                case BOOLEAN:
                    val.append(numbers.charAt(getRandomInt(0, 1)));
                    break;
                case STRING:
                    val.append(column.getDefaultValue() != null ? column.getDefaultValue() : chars.charAt(getRandomInt(0, 25)));
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

        try
        {
            return column.format(val.toString(), column);
        }
        catch (Exception e)
        {
            System.out.println("Fatal fill value " + e.getMessage());
            System.exit(-1);
            return "";
        }
    }


    private int getRandomInt(int min, int max)
    {
        return (int) ((Math.random() * max) + 1);
    }
}
