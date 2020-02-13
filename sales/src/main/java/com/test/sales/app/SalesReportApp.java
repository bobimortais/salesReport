package com.test.sales.app;

import com.test.sales.watcher.FilesWatcherService;

import java.io.IOException;

public class SalesReportApp
{
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
