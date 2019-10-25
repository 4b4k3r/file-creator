package com.ks;

import com.ks.exception.ControlledException;
import com.ks.fileProcessing.FileManager;

import java.util.Arrays;
import java.util.Optional;

public class Application
{
    public static void main(String[] args)
    {
        try
        {
            new FileManager().start(Integer.parseInt(validateProperty("fileMB")), validateProperty("fileUrl"));
        }
        catch (ControlledException ce)
        {
            System.out.println("Error controlled: " + ce.getMessage());
            System.exit(-1);
        }
        catch (Exception e)
        {
            System.out.println("Error fatal: " + e.getMessage() + " localized on to: " + Arrays.toString(e.getStackTrace()));
            System.exit(-1);
        }
    }

    private static String validateProperty(String property) throws ControlledException
    {
        String returnValue = System.getProperty(property);
        return Optional.ofNullable(returnValue).orElseThrow(() -> new ControlledException("Property " + property + " not defined"));
    }
}
