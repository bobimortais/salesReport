package com.test.sales.watcher;

public final class EnvironmentConstants
{
    private static EnvironmentConstants instance;
    private final String HOMEPATH;

    private final String INPUT_FILE_DIR;

    private final String OUTPUT_FILE_DIR;

    private final String PROCESSED_FILE_DIR;

    private final String OUTPUT_FILE_EXTENSION;

    private final String INPUT_FILE_COLUMN_SEPARATOR;

    private final String ITEM_LIST_SEPARATOR;

    private final String ITEM_FIELD_SEPARATOR;

    private final String SELLER_IDENTIFIER;

    private final String CUSTOMER_IDENTIFIER;

    private final String SALE_IDENTIFIER;

    private EnvironmentConstants()
    {
        instance = this;
        HOMEPATH = System.getenv("HOMEPATH");
        INPUT_FILE_DIR = HOMEPATH + "/data/in/";
        OUTPUT_FILE_DIR = HOMEPATH + "/data/out/";
        PROCESSED_FILE_DIR = HOMEPATH + "/data/prd/";
        OUTPUT_FILE_EXTENSION = ".txt";
        INPUT_FILE_COLUMN_SEPARATOR = "ç";
        ITEM_LIST_SEPARATOR = ",";
        ITEM_FIELD_SEPARATOR = "-";
        SELLER_IDENTIFIER = "001";
        CUSTOMER_IDENTIFIER = "002";
        SALE_IDENTIFIER = "003";
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

    public String getOUTPUT_FILE_EXTENSION()
    {
        return OUTPUT_FILE_EXTENSION;
    }

    public String getINPUT_FILE_COLUMN_SEPARATOR()
    {
        return INPUT_FILE_COLUMN_SEPARATOR;
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
