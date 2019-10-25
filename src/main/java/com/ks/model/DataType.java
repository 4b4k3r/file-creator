package com.ks.model;

public enum DataType
{
    INTEGER(null),
    STRING(null),
    BOOLEAN(null),
    DATE("yyyy-MM-dd"),
    TIME("HH:mm:ss");

    private String format;

    DataType(String format)
    {
        this.format = format;
    }

    public String getFormat()
    {
        return format;
    }
}
