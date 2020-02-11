package com.test.sales.app;

import java.io.IOException;
import java.nio.file.*;

public class SalesReportApp
{
    public static void main(String[] args)
    {
        System.out.println("Sales Report App");
        try
        {
            registerPathToWatch();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void registerPathToWatch() throws IOException
    {
        String envVarValue = System.getenv("HOMEPATH");
        String pathToRead = envVarValue + "/data/in";
        Path path = Paths.get(pathToRead);
        WatchService watcher =  path.getFileSystem().newWatchService();
        path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
        handleEvents(watcher);
    }

    private static void handleEvents(WatchService watcher)
    {
        while (true)
        {
            WatchKey key;
            try
            {
                key = watcher.take();
            }
            catch (InterruptedException ex)
            {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents())
            {
                WatchEvent.Kind<?> kind = event.kind();
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();

                System.out.println(kind.name() + ": " + fileName);

                if (kind == StandardWatchEventKinds.ENTRY_CREATE)
                {
                    System.out.println("Novo Arquivo Criado");
                }
            }

            boolean valid = key.reset();
            if (!valid)
            {
                break;
            }
        }
    }
}
