package com.test.sales.watcher;

import com.test.sales.processor.FileProcessor;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FilesWatcherService
{
    private WatchService watcher;

    public FilesWatcherService() throws IOException
    {
        Path path = Paths.get(AppConstants.INPUT_FILE_DIR);
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
        ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(AppConstants.THREAD_POOL_SIZE));

        //Keep loop going indefinetly to keep checking input directory for new files
        while (true)
        {
            try
            {
                key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents())
                {
                    Path fileName = ((WatchEvent<Path>) event).context();
                    //This sleep on thread is necessary to avoid two actions over the same file
                    //Which could cause a lock exception
                    Thread.sleep( 50 );
                    System.out.println("Arquivo criado: " + fileName);
                    FileProcessor processor = new FileProcessor(fileName);
                    executor.execute(processor);
                    System.out.println("Partindo para próximo arquivo");
                }

                if(!key.reset())
                    break;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return;
            }
        }
    }
}
