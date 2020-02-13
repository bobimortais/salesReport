package com.test.sales.app;

import com.test.sales.watcher.FilesWatcherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class SalesReportApp
{
    private static final Logger logger = LogManager.getLogger(SalesReportApp.class);

    public static void main(String[] args)
    {
        System.out.println("Sales Report App");
        try
        {
            FilesWatcherService watcherService = new FilesWatcherService();
            watcherService.handleEvents();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
