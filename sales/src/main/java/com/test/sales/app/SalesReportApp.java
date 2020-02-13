package com.test.sales.app;

import com.test.sales.watcher.FilesWatcherService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

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

    private static boolean checkEnvironment()
    {
        boolean isEnvironmentSet = true;


        return isEnvironmentSet;
    }
}
