package com.test.sales.processor;

import com.test.sales.entity.FileInfo;
import com.test.sales.parser.InputFileParser;
import com.test.sales.watcher.EnvironmentConstants;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessor implements Runnable
{
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
        this.processedFile = processedFile;
    }
    private void processFile() throws IOException
    {
        System.out.println("Processing file");
        Path file = Paths.get(EnvironmentConstants.getInstance().getINPUT_FILE_DIR() + processedFile);
        FileInfo fileInfo = InputFileParser.getFileInfo(file);
        moveProcessedFile(processedFile);
        writeOutputFile(processedFile);
    }

    private void moveProcessedFile(Path processedFile) throws IOException
    {
        String pathToMove = EnvironmentConstants.getInstance().getPROCESSED_FILE_DIR() + processedFile;
        String pathToRead = EnvironmentConstants.getInstance().getINPUT_FILE_DIR() + processedFile;
        Files.move(Paths.get(pathToRead), Paths.get(pathToMove));
    }

    private void writeOutputFile(Path processedFile) throws IOException
    {
        String pathToWrite = EnvironmentConstants.getInstance().getOUTPUT_FILE_DIR();
        FileWriter fileWriter = new FileWriter(pathToWrite + processedFile + "_output" + EnvironmentConstants.getInstance().getOUTPUT_FILE_EXTENSION());
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("Teste arquivo gerado");
        printWriter.close();
    }
}
