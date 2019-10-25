# file creator

##General 
This project create a file taking a layout about the content of the file and
the file size can be defined by VMoptions 

>NOTE: Memory heap usage is recommended

VMOPTIONS

```bash
-Xmx100m
-DfileMB=20480
-DfileUrl=C:\Users\JoelMedina\Desktop\liverpoolBatchTest\test20GB.txt
```

|vmoption   | description|
|-----------|---------------------|
|fileUrl    |File output directory|
|fileMB     |File size goal in MB |
|Xmx        |Memory size heap followed by the size and size unity|

#TODO

- Layout in XML or JSON format (not by hardcode)