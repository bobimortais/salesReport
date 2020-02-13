package com.test.sales.processor;

import com.test.sales.entity.FileInfo;
import com.test.sales.parser.InputFileParser;
import com.test.sales.watcher.AppConstants;
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

    /**
     * Method to process input file
     * @throws IOException
     */
    private void processFile() throws IOException
    {
        System.out.println("Processing file");
        Path file = Paths.get(AppConstants.INPUT_FILE_DIR + processedFile);
        FileInfo fileInfo = InputFileParser.getFileInfo(file);
        moveProcessedFile(processedFile);
        writeOutputFile(processedFile, fileInfo);
    }

    /**
     * Method to move input file already processed to prd folder
     * @param processedFile - file processed
     * @throws IOException
     */
    private void moveProcessedFile(Path processedFile) throws IOException
    {
        String pathToMove = AppConstants.PROCESSED_FILE_DIR + processedFile;
        String pathToRead = AppConstants.INPUT_FILE_DIR + processedFile;
        Files.move(Paths.get(pathToRead), Paths.get(pathToMove));
    }

    /**
     * Method to write output file to out folder
     * @param processedFile - file processed
     * @param fileInfo - information parsed from input file
     * @throws IOException
     */
    private void writeOutputFile(Path processedFile, FileInfo fileInfo) throws IOException
    {
        String pathToWrite = AppConstants.OUTPUT_FILE_DIR;
        FileWriter fileWriter = new FileWriter(pathToWrite + processedFile + "_output" + AppConstants.OUTPUT_FILE_EXTENSION);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Quantidade de clientes no arquivo de entrada: " + fileInfo.getCustomers().size());
        printWriter.println("Quantidade de vendedores no arquivo de entrada: " + fileInfo.getSellers().size());
        printWriter.println("ID da venda mais cara:" + fileInfo.getBestSaleId());
        printWriter.print("Nome Pior vendedor:" + fileInfo.getWorstSeller());
        printWriter.close();
    }
}
