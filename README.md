# file creator

##General 
You can reate a file taking a layout about the content of the file and
the file size can be defined by properties file

>NOTE: Memory heap usage is recommended

VMOPTIONS

```bash
-Xmx100m
-DfileMB=20480
-Dconfiguration.file=./config/application.properties
```

|vmoption   | description|
|-----------|---------------------|
|configuration.file|Configuration file|

|property|description|
|--------|-----------|
|file.size|File size goal in MB |
|file.url|File url with path and extension|
|columns.file|File with colums for file fill|

A layout file must have a structure like: 

|name|type|
|----|----|
|columns|Array of column object|
|columns/column|Column object|
|columns/column/name|A name of the column (Alphanumeric)|
|columns/column/length|A length of the column (Integer value)|
|columns/column/Type|A type of the column [INTEGER, STRING, BOOLEAN_STRING, BOOLEAN_INTEGER, DATE, TIME]|
|columns/column/defaultValue|A value to fill this column always (Alphanumeric)|

>*NOTE*: A column length always be respected except  DATE (format `yyyy-MM-dd`) and TIME (`HH:mm:ss`)


#####🎉 🤓 TODO
- Optional date/time/datetime parametric formats
- custom Regex fields
- And more ...