package com.test.sales.processor;

import com.test.sales.entity.FileInfo;
import com.test.sales.entity.Item;
import com.test.sales.entity.Sale;
import com.test.sales.entity.Seller;
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

    /**
     * Method to process input file
     * @throws IOException
     */
    private void processFile() throws IOException
    {
        System.out.println("Processing file");
        Path file = Paths.get(EnvironmentConstants.getInstance().getINPUT_FILE_DIR() + processedFile);
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
        String pathToMove = EnvironmentConstants.getInstance().getPROCESSED_FILE_DIR() + processedFile;
        String pathToRead = EnvironmentConstants.getInstance().getINPUT_FILE_DIR() + processedFile;
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
        String pathToWrite = EnvironmentConstants.getInstance().getOUTPUT_FILE_DIR();
        FileWriter fileWriter = new FileWriter(pathToWrite + processedFile + "_output" + EnvironmentConstants.getInstance().getOUTPUT_FILE_EXTENSION());
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Quantidade de clientes no arquivo de entrada: " + fileInfo.getCustomers().size());
        printWriter.println("Quantidade de vendedores no arquivo de entrada: " + fileInfo.getSellers().size());
        printWriter.println("ID da venda mais cara:" + getBestSaleId(fileInfo));
        printWriter.println("Nome Pior vendedor:" + getWorstSeller(fileInfo));
        printWriter.close();
    }

    /**
     * Method to get worst seller contained on input file
     * @param fileInfo - information parsed from input file
     * @return String - worst seller name
     */
    private String getWorstSeller(FileInfo fileInfo)
    {
        String worstSeller = fileInfo.getSellers().get(0).getName();
        double worstSellerSales = 0;
        int i = 0;

        for(Seller seller : fileInfo.getSellers())
        {
            double sellerTotalSalesValue = 0;
            List<Sale> sales = fileInfo.getSales().stream().filter(sale -> sale.getSalesMan().equals(seller.getName())).collect(Collectors.toList());

            for(Sale sale : sales)
            {
                for(Item item : sale.getItems())
                {
                    sellerTotalSalesValue += item.getItemPrice() * item.getItemQuantity();
                }
            }

            if(i == 0)
                worstSellerSales = sellerTotalSalesValue;

            if(sellerTotalSalesValue < worstSellerSales)
            {
                worstSeller = seller.getName();
                worstSellerSales = sellerTotalSalesValue;
            }
            i++;
        }
        return worstSeller;
    }

    /**
     * Method to get id of the best sale contained on input file
     * @param fileInfo - information parsed from input file
     * @return long - id of the best sale
     */
    private long getBestSaleId(FileInfo fileInfo)
    {
        List<Sale> sortedList = fileInfo.getSales().stream().sorted((s1, s2) -> s2.getItems().stream().collect(Collectors.summingDouble(item -> item.getItemQuantity() * item.getItemPrice())).compareTo(s1.getItems().stream().collect(Collectors.summingDouble(item -> item.getItemQuantity() * item.getItemPrice())))).collect(Collectors.toList());
        long bestSaleId = sortedList.get(0).getSaleId();
        return bestSaleId;
    }
}
