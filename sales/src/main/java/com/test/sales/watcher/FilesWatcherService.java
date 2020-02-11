package com.test.sales.watcher;

import com.test.sales.processor.FileProcessor;
import java.io.IOException;
import java.nio.file.*;

public class FilesWatcherService
{
    private WatchService watcher;

    public FilesWatcherService() throws IOException
    {
        Path path = Paths.get(EnvironmentConstants.getInstance().getINPUT_FILE_DIR());
        watcher = path.getFileSystem().newWatchService();
        path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
    }

    public void handleEvents()
    {
        WatchKey key;
        while (true)
        {
            try
            {
                key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents())
                {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE)
                    {
                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        Path fileName = ev.context();
                        System.out.println("Arquivo criado: " + fileName);
                        FileProcessor processor = new FileProcessor(fileName);
                        processor.run();
                    }
                }

                boolean valid = key.reset();
                if (!valid)
                {
                    break;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                return;
            }
        }
    }
}
