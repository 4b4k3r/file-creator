package com.ks;

import com.fasterxml.jackson.xml.XmlMapper;
import com.ks.fileProcessing.FileManager;
import com.ks.model.Column;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

public class Application {
    private static final XmlMapper mapper = new XmlMapper();
    private static final Properties properties = new Properties();

    public static void main(String[] args) {
        try {
            properties.load(new InputStreamReader(new FileInputStream(new File(validateEnv("configuration.file")))));
            FileManager.columns = Arrays.asList(mapper.readValue(new File(properties.getProperty("columns.file")), Column[].class));
            new FileManager().start(Integer.parseInt(validateProperty("file.size")), validateProperty("file.url"));
        } catch (Exception e) {
            System.out.println("Error fatal: " + e.getMessage() + " localized on to: " + Arrays.toString(e.getStackTrace()));
            System.exit(-1);
        }
    }

    private static String validateEnv(String property) throws IllegalArgumentException {
        return Optional.ofNullable(System.getProperty(property)).orElseThrow(() -> new IllegalArgumentException("Env Property " + property + " not defined"));
    }

    private static String validateProperty(String property) throws IllegalArgumentException {
        return Optional.ofNullable(properties.getProperty(property)).orElseThrow(() -> new IllegalArgumentException("File Property " + property + " not defined"));
    }
}
