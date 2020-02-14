package com.test.sales.watcher;

import com.test.sales.app.SalesReportApp;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class AppConstants
{
    private static final Logger logger = LogManager.getLogger(AppConstants.class);

    public static final String HOMEPATH;

    public static final String INPUT_FILE_DIR;

    public static final String OUTPUT_FILE_DIR;

    public static final String PROCESSED_FILE_DIR;

    public static final String FAILED_DIR;

    public static final String OUTPUT_FILE_EXTENSION;

    public static final String INPUT_FILE_COLUMN_SEPARATOR;

    public static final String ITEM_LIST_BLOCK_OPENER;

    public static final String ITEM_LIST_BLOCK_CLOSER;

    public static final String ITEM_LIST_SEPARATOR;

    public static final String ITEM_FIELD_SEPARATOR;

    public static final String SELLER_IDENTIFIER;

    public static final String CUSTOMER_IDENTIFIER;

    public static final String SALE_IDENTIFIER;

    public static final String UNDEFINED_WORST_SELLER;

    public static final String THREAD_POOL_SIZE;

    static
    {
        Properties properties = new Properties();

        try
        {
            properties.load(AppConstants.class.getClassLoader().getResourceAsStream("app.properties"));
        }
        catch (FileNotFoundException e)
        {
           logger.log(Level.ERROR, "Falha na leitura de arquivo properties", e);
        }
        catch (IOException e)
        {
            logger.log(Level.ERROR, "Falha na leitura de arquivo properties", e);
        }

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
        UNDEFINED_WORST_SELLER = properties.getProperty("UNDEFINED_WORST_SELLER");
        THREAD_POOL_SIZE = properties.getProperty("THREAD_POOL_SIZE");
    }

    /**
     * Private constructor to avoid the multiple instantiation of the class
     * on the JVM
     */
    private AppConstants()
    {

    }
}
