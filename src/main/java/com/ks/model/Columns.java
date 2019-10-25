package com.ks.model;

public enum Columns
{
    NAME("nombre", DataType.STRING, 60, null),
    LAST_NAME("lastName", DataType.STRING, 60, null),
    BORN_DATE("bornDate", DataType.DATE, 10, null),
    CELL_PHONE("cellPhone", DataType.INTEGER, 10, null),
    HOME_PHONE("homePhone", DataType.INTEGER, 10, null),
    STREET("street", DataType.STRING, 20, null),
    HOME_NUMBER("homeNumber", DataType.INTEGER, 5, null),
    CP("cp", DataType.INTEGER, 5, null),
    TRANSACTION_ID("transactionId", DataType.INTEGER, 10, null),
    AMOUNT("amount", DataType.INTEGER, 10, null),
    CARD_NUMBER("cardNumber", DataType.STRING, 16, null),
    CVV("cvv", DataType.INTEGER, 4, null),
    BANK("bankName", DataType.STRING, 15, null),
    SHOP_NUMBER("shopNumber", DataType.INTEGER, 5, null),
    COUNTRY("countryName", DataType.STRING, 20, null),
    TRANSACTION_DATE("transactionDate", DataType.DATE, 10, null),
    TRANSACTION_TIME("transactionTime", DataType.TIME, 8, null),
    AUTHORIZATION_CODE("authorizationCode", DataType.STRING, 2, "00"),
    SHOP_NAME("shopName", DataType.STRING, 20, null),
    FRAUD("fraud", DataType.BOOLEAN, 1, null);

    private String name;
    private DataType dataType;
    private int length;
    private String defaultValue;

    Columns(String name, DataType dataType, int length, String defaultValue)
    {
        this.name = name;
        this.dataType = dataType;
        this.length = length;
        this.defaultValue = defaultValue;
    }

    public String format(String val, Columns column) throws Exception
    {
        switch (column.dataType)
        {
            case DATE:
            case TIME:
                return val;
            case STRING:
                return String.format("%" + column.length + "s", (column.defaultValue != null ? column.defaultValue : String.valueOf(val)));
            case BOOLEAN:
                return Boolean.toString(Boolean.parseBoolean(String.valueOf(val)));
            case INTEGER:
                return String.format("%" + column.length + "s", (column.defaultValue != null ? column.defaultValue : String.valueOf(val))).replace(" ", "0");
            default:
                throw new Exception("no data type defined");
        }
    }

    public String getName()
    {
        return name;
    }

    public DataType getDataType()
    {
        return dataType;
    }

    public int getLength()
    {
        return length;
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }
}
