package com.test.sales.processor;

import com.test.sales.app.SalesReportApp;
import com.test.sales.entity.FileInfo;
import com.test.sales.parser.InputFileParser;
import com.test.sales.watcher.AppConstants;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileProcessor implements Runnable
{
    private Path processedFile;

    private static final Logger logger = LogManager.getLogger(SalesReportApp.class);

    @Override
    public void run()
    {
        try
        {
            processFile();
        }
        catch (IOException e)
        {
            logger.log(Level.ERROR, "Falha na aplicação", e);
        }
    }

    public FileProcessor(Path processedFile)
    {
        this.processedFile = processedFile;
    }

    /**
     * Method to process input file
     * @throws IOException
     */
    private void processFile() throws IOException
    {
        logger.info("Processing file " + processedFile);
        Path file = Paths.get(AppConstants.INPUT_FILE_DIR + processedFile);

        try
        {
            FileInfo fileInfo = InputFileParser.getFileInfo(file);
            moveProcessedFile(processedFile);
            writeOutputFile(processedFile, fileInfo);
        }
        catch (Exception e)
        {
            logger.error("Falha no processamento do arquivo " + processedFile, e);
            moveFailedFile(processedFile);
        }
        logger.info("Finalizado processamento do arquivo " + processedFile);
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
        Files.move(Paths.get(pathToRead), Paths.get(pathToMove), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Method to move input file which failed on processing to fld folder
     * @param processedFile - file processed
     * @throws IOException
     */
    private void moveFailedFile(Path processedFile) throws IOException
    {
        String pathToMove = AppConstants.FAILED_DIR + processedFile;
        String pathToRead = AppConstants.INPUT_FILE_DIR + processedFile;
        Files.move(Paths.get(pathToRead), Paths.get(pathToMove), StandardCopyOption.REPLACE_EXISTING);
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
        printWriter.println("ID da venda mais cara: " + fileInfo.getBestSaleId());
        printWriter.print("Nome Pior vendedor: " + fileInfo.getWorstSeller());
        printWriter.close();
    }
}
