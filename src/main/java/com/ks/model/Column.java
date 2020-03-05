package com.ks.model;

import lombok.Data;

@Data
public class Column {
    private String name;
    private DataType type;
    private int length;
    private String defaultValue;
    private String regex;

    public String format(String val) throws IllegalArgumentException {
        switch (type) {
            case DATE:
            case TIME:
                return val;
            case STRING:
            case BOOLEAN_INTEGER:
                return String.format("%" + length + "s", (defaultValue != null && !defaultValue.isEmpty() ? defaultValue : val));
            case BOOLEAN_STRING:
                return String.format("%" + length + "s", defaultValue != null && !defaultValue.isEmpty() ? Boolean.parseBoolean(defaultValue) : Boolean.parseBoolean(val));
            case INTEGER:
                return String.format("%" + length + "s", (defaultValue != null && !defaultValue.isEmpty() ? defaultValue : val)).replace(" ", "0");
            default:
                throw new IllegalArgumentException("no data type defined");
        }
    }
}
