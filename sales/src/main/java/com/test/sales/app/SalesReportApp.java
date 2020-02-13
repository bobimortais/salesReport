package com.test.sales.app;

import com.test.sales.watcher.AppConstants;
import com.test.sales.watcher.FilesWatcherService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class SalesReportApp
{
    private static final Logger logger = LogManager.getLogger(SalesReportApp.class);

    public static void main(String[] args)
    {
        logger.info("Sales Report App");
        try
        {
            if(checkEnvironment())
            {
                FilesWatcherService watcherService = new FilesWatcherService();
                watcherService.handleEvents();
            }
        }
        catch (IOException e)
        {
            logger.log(Level.ERROR, "Falha na aplicação", e);
        }
        catch(Exception e)
        {
            logger.log(Level.ERROR, "Falha na aplicação", e);
        }
    }

    /**
     * Method to check if the environment is properly set prior execution
     * @return boolean - isEnvironmentSet
     */
    private static boolean checkEnvironment()
    {
        boolean isEnvironmentSet = true;

        try
        {
            if (Files.notExists(Paths.get(AppConstants.INPUT_FILE_DIR)))
            {
                throw new NoSuchFileException("Pasta de input data/in não existente");
            }
            else if (Files.notExists(Paths.get(AppConstants.PROCESSED_FILE_DIR)))
            {
                throw new NoSuchFileException("Pasta de processamento data/prd não existente");
            }
            else if (Files.notExists(Paths.get(AppConstants.OUTPUT_FILE_DIR)))
            {
                throw new NoSuchFileException("Pasta de output data/out não existente");
            }
            else if (Files.notExists(Paths.get(AppConstants.FAILED_DIR)))
            {
                throw new NoSuchFileException("Pasta de falhas data/fld não existente");
            }
        }
        catch(NoSuchFileException e)
        {
            logger.error("Falha na aplicação", e);
            isEnvironmentSet = false;
        }
        return isEnvironmentSet;
    }
}
