package com.test.sales.processor;

import com.test.sales.entity.FileInfo;
import com.test.sales.entity.Sale;
import com.test.sales.parser.InputFileParser;
import com.test.sales.watcher.EnvironmentConstants;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
        writeOutputFile(processedFile, fileInfo);
    }

    private void moveProcessedFile(Path processedFile) throws IOException
    {
        String pathToMove = EnvironmentConstants.getInstance().getPROCESSED_FILE_DIR() + processedFile;
        String pathToRead = EnvironmentConstants.getInstance().getINPUT_FILE_DIR() + processedFile;
        Files.move(Paths.get(pathToRead), Paths.get(pathToMove));
    }

    private void writeOutputFile(Path processedFile, FileInfo fileInfo) throws IOException
    {
        String pathToWrite = EnvironmentConstants.getInstance().getOUTPUT_FILE_DIR();
        FileWriter fileWriter = new FileWriter(pathToWrite + processedFile + "_output" + EnvironmentConstants.getInstance().getOUTPUT_FILE_EXTENSION());
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Quantidade de clientes no arquivo de entrada: " + fileInfo.getCustomers().size());
        printWriter.println("Quantidade de vendedores no arquivo de entrada: " + fileInfo.getSellers().size());
        printWriter.println("ID da venda mais cara:" + getBestSaleId(fileInfo));
        printWriter.println("ID Pior vendedor:" + getWorstSellerId(fileInfo));
        printWriter.close();
    }

    private long getWorstSellerId(FileInfo fileInfo)
    {
        long worstSellerId = 0;
        return worstSellerId;
    }

    private long getBestSaleId(FileInfo fileInfo)
    {
        List<Sale> sortedList = fileInfo.getSales().stream().sorted((s1, s2) -> s2.getItems().stream().collect(Collectors.summingDouble(item -> item.getItemQuantity() * item.getItemPrice())).compareTo(s1.getItems().stream().collect(Collectors.summingDouble(item -> item.getItemQuantity() * item.getItemPrice())))).collect(Collectors.toList());
        long bestSaleId = sortedList.get(0).getSaleId();
        return bestSaleId;
    }
}
