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

    /**
     * Method to handler creation events on input folder. Its has a infinite loop
     * which will check for any creation event on the input folder and will dispatch
     * a new thread to parse the input file and generate the report to the output folder
     */
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
