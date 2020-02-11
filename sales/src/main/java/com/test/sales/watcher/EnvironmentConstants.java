package com.test.sales.watcher;

public final class EnvironmentConstants
{
    public final String HOMEPATH;

    public final String INPUT_FILE_DIR;

    public final String OUTPUT_FILE_DIR;

    public final String PROCESSED_FILE_DIR;

    public final String OUTPUT_FILE_EXTENSION;

    public final String INPUT_FILE_COLUMN_SEPARATOR;

    public final String SELLER_IDENTIFIER;

    public final String CUSTOMER_IDENTIFIER;

    public final String SALE_IDENTIFIER;

    public EnvironmentConstants()
    {
        HOMEPATH = System.getenv("HOMEPATH");
        INPUT_FILE_DIR = HOMEPATH + "/data/in/";
        OUTPUT_FILE_DIR = HOMEPATH + "/data/out/";
        PROCESSED_FILE_DIR = HOMEPATH + "/data/prd/";
        OUTPUT_FILE_EXTENSION = ".txt";
        INPUT_FILE_COLUMN_SEPARATOR = "ç";
        SELLER_IDENTIFIER = "001";
        CUSTOMER_IDENTIFIER = "002";
        SALE_IDENTIFIER = "003";
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
