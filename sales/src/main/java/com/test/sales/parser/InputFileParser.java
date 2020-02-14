package com.test.sales.parser;

import com.test.sales.app.SalesReportApp;
import com.test.sales.entity.*;
import com.test.sales.watcher.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InputFileParser
{
    private static final Logger logger = LogManager.getLogger(InputFileParser.class);
    /**
     * Method to parse information from input file
     * @param file - file to be parsed
     * @return FileInfo - class which encapsulates information from input file
     * @throws IOException
     */
    public static FileInfo getFileInfo(Path file) throws Exception
    {
        Stream<String> lines = Files.lines(file);
        FileInfo fileInfo = new FileInfo();

        lines.forEach(element -> {
            String[] columns = element.split(AppConstants.INPUT_FILE_COLUMN_SEPARATOR);

            if(columns[0].equals(AppConstants.SELLER_IDENTIFIER))
            {
                fileInfo.getSellers().add(parseSellerInfo(columns));
            }
            else if(columns[0].equals(AppConstants.CUSTOMER_IDENTIFIER))
            {
                fileInfo.getCustomers().add(parseCustomerInfo(columns));
            }
            else if(columns[0].equals(AppConstants.SALE_IDENTIFIER))
            {
                fileInfo.getSales().add(parseSaleInfo(columns));
            }
        }
        );

        if(fileInfo.getCustomers().isEmpty() || fileInfo.getSellers().isEmpty() || fileInfo.getSales().isEmpty())
            throw new Exception("Arquivo " + file + " não contém informações válidas");

        lines.close();
        return fileInfo;
    }

    /**
     * Method to parse seller information
     * @param columns - seller information from input file
     * @return Seller - seller info
     */
    private static Seller parseSellerInfo(String[] columns)
    {
        Seller seller = new Seller();
        seller.setCpf(columns[1]);
        seller.setName(columns[2]);
        seller.setSalary(Double.parseDouble(columns[3]));
        return seller;
    }

    /**
     * Method to parse customer information
     * @param columns - customer information from input file
     * @return Customer - customer info
     */
    private static Customer parseCustomerInfo(String[] columns)
    {
        Customer customer = new Customer();
        customer.setCnpj(columns[1]);
        customer.setName(columns[2]);
        customer.setBusinessArea(columns[3]);
        return customer;
    }

    /**
     * Method to parse sale information
     * @param columns - sale information from input file
     * @return Sale - sale info
     */
    private static Sale parseSaleInfo(String[] columns)
    {
        Sale sale = new Sale();
        sale.setSaleId(Long.parseLong(columns[1]));
        sale.setItems(parseItemsInfo(columns[2]));
        sale.setSalesMan(columns[3]);
        return sale;
    }

    /**
     * Method to parse item information from a sale
     * @param itemsInfo - item information from input file for a specific sale
     * @return List<Item> - list of items of the input sale
     */
    private static List<Item> parseItemsInfo(String itemsInfo)
    {
        List<Item> items = new ArrayList<>();
        String[] itemData = itemsInfo.split(AppConstants.ITEM_LIST_SEPARATOR);

        for(String data : itemData)
        {
            String[] itemFields = data.split(AppConstants.ITEM_FIELD_SEPARATOR);
            Item item = new Item();
            item.setItemId(Long.parseLong(itemFields[0].replace(AppConstants.ITEM_LIST_BLOCK_OPENER, "")));
            item.setItemQuantity(Integer.parseInt(itemFields[1]));
            item.setItemPrice(Double.parseDouble(itemFields[2].replace(AppConstants.ITEM_LIST_BLOCK_CLOSER, "")));
            items.add(item);
        }

        return items;
    }
}
