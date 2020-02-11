package com.test.sales.processor;

import com.test.sales.watcher.EnvironmentConstants;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessor implements Runnable
{
    private EnvironmentConstants constants;

    private Path processedFile;

    @Override
    public void run()
    {
        try
        {
            processFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public FileProcessor(Path processedFile) throws IOException
    {
        constants = new EnvironmentConstants();
        this.processedFile = processedFile;
    }
    private void processFile() throws IOException
    {
        System.out.println("Processing file");
        moveProcessedFile(processedFile);
        writeOutputFile(processedFile);
    }

    private void moveProcessedFile(Path processedFile) throws IOException
    {
        String pathToMove = constants.getPROCESSED_FILE_DIR() + processedFile;
        String pathToRead = constants.getINPUT_FILE_DIR() + processedFile;
        Files.move(Paths.get(pathToRead), Paths.get(pathToMove));
    }

    private void writeOutputFile(Path processedFile) throws IOException
    {
        String pathToWrite = constants.getOUTPUT_FILE_DIR();
        FileWriter fileWriter = new FileWriter(pathToWrite + processedFile + "_output" + constants.getOUTPUT_FILE_EXTENSION());
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("Teste arquivo gerado");
        printWriter.close();
    }
}
