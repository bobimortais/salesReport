package com.test.sales.watcher;

import com.test.sales.processor.FileProcessor;
import java.io.IOException;
import java.nio.file.*;

public class FilesWatcherService
{
    private WatchService watcher;

    private EnvironmentConstants constants;

    public FilesWatcherService() throws IOException
    {
        constants = new EnvironmentConstants();
        Path path = Paths.get(constants.getINPUT_FILE_DIR());
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
                    WatchEvent.Kind<?> kind = event.kind();
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();

                    System.out.println(kind.name() + ": " + fileName);

                    if (kind == StandardWatchEventKinds.ENTRY_CREATE)
                    {
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
