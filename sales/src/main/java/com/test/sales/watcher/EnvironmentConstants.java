package com.test.sales.watcher;

import java.io.IOException;
import java.util.Properties;

public final class EnvironmentConstants
{
    private static EnvironmentConstants instance;

    private final String HOMEPATH;

    private final String INPUT_FILE_DIR;

    private final String OUTPUT_FILE_DIR;

    private final String PROCESSED_FILE_DIR;

    private final String FAILED_DIR;

    private final String OUTPUT_FILE_EXTENSION;

    private final String INPUT_FILE_COLUMN_SEPARATOR;

    private final String ITEM_LIST_BLOCK_OPENER;

    private final String ITEM_LIST_BLOCK_CLOSER;

    private final String ITEM_LIST_SEPARATOR;

    private final String ITEM_FIELD_SEPARATOR;

    private final String SELLER_IDENTIFIER;

    private final String CUSTOMER_IDENTIFIER;

    private final String SALE_IDENTIFIER;

    private EnvironmentConstants()
    {
        Properties properties = new Properties();

        try
        {
            properties.load(getClass().getClassLoader().getResourceAsStream("app.properties"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        instance = this;
        HOMEPATH = System.getenv("HOMEPATH");
        INPUT_FILE_DIR = HOMEPATH + properties.getProperty("INPUT_FILE_DIR");
        OUTPUT_FILE_DIR = HOMEPATH + properties.getProperty("OUTPUT_FILE_DIR");
        PROCESSED_FILE_DIR = HOMEPATH + properties.getProperty("PROCESSED_FILE_DIR");
        FAILED_DIR = HOMEPATH + properties.getProperty("FAILED_DIR");
        OUTPUT_FILE_EXTENSION = properties.getProperty("OUTPUT_FILE_EXTENSION");
        INPUT_FILE_COLUMN_SEPARATOR = properties.getProperty("INPUT_FILE_COLUMN_SEPARATOR");
        ITEM_LIST_BLOCK_OPENER = properties.getProperty("ITEM_LIST_BLOCK_OPENER");
        ITEM_LIST_BLOCK_CLOSER = properties.getProperty("ITEM_LIST_BLOCK_CLOSER");
        ITEM_LIST_SEPARATOR = properties.getProperty("ITEM_LIST_SEPARATOR");
        ITEM_FIELD_SEPARATOR = properties.getProperty("ITEM_FIELD_SEPARATOR");
        SELLER_IDENTIFIER = properties.getProperty("SELLER_IDENTIFIER");
        CUSTOMER_IDENTIFIER = properties.getProperty("CUSTOMER_IDENTIFIER");
        SALE_IDENTIFIER = properties.getProperty("SALE_IDENTIFIER");
    }

    public static EnvironmentConstants getInstance()
    {
        if(instance == null)
        {
            instance = new EnvironmentConstants();
        }
        return instance;
    }

    public String getHOMEPATH()
    {
        return HOMEPATH;
    }

    public String getINPUT_FILE_DIR()
    {
        return INPUT_FILE_DIR;
    }

    public String getOUTPUT_FILE_DIR()
    {
        return OUTPUT_FILE_DIR;
    }

    public String getPROCESSED_FILE_DIR()
    {
        return PROCESSED_FILE_DIR;
    }

    public String getFAILED_DIR()
    {
        return FAILED_DIR;
    }

    public String getOUTPUT_FILE_EXTENSION()
    {
        return OUTPUT_FILE_EXTENSION;
    }

    public String getINPUT_FILE_COLUMN_SEPARATOR()
    {
        return INPUT_FILE_COLUMN_SEPARATOR;
    }

    public String getITEM_LIST_BLOCK_OPENER()
    {
        return ITEM_LIST_BLOCK_OPENER;
    }

    public String getITEM_LIST_BLOCK_CLOSER()
    {
        return ITEM_LIST_BLOCK_CLOSER;
    }

    public String getITEM_LIST_SEPARATOR()
    {
        return ITEM_LIST_SEPARATOR;
    }

    public String getITEM_FIELD_SEPARATOR()
    {
        return ITEM_FIELD_SEPARATOR;
    }

    public String getSELLER_IDENTIFIER()
    {
        return SELLER_IDENTIFIER;
    }

    public String getCUSTOMER_IDENTIFIER()
    {
        return CUSTOMER_IDENTIFIER;
    }

    public String getSALE_IDENTIFIER()
    {
        return SALE_IDENTIFIER;
    }
}
