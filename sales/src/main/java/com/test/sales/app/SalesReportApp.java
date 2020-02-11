package com.test.sales.app;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
                for (WatchEvent<?> event : key.pollEvents())
                {
                    WatchEvent.Kind<?> kind = event.kind();
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();

                    System.out.println(kind.name() + ": " + fileName);

                    if (kind == StandardWatchEventKinds.ENTRY_CREATE)
                    {
                        moveProcessedFile(fileName);
                        System.out.println("Novo Arquivo Criado");
                        writeOutputFile();
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

    private static void handleFileCreation()
    {

    }

    private static void moveProcessedFile(Path processedFile) throws IOException
    {
        String envVarValue = System.getenv("HOMEPATH");
        String pathToMove = envVarValue + "/data/prd/" + processedFile;
        String pathToRead = envVarValue + "/data/in/" + processedFile;
        Files.move(Paths.get(pathToRead), Paths.get(pathToMove));
    }

    private static void writeOutputFile() throws IOException
    {
        String envVarValue = System.getenv("HOMEPATH");
        String pathToWrite = envVarValue + "/data/out";
        FileWriter fileWriter = new FileWriter(pathToWrite + "/robson.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("Teste arquivo gerado");
        printWriter.close();
    }
}
