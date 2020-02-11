package com.test.sales.parser;

import com.test.sales.entity.*;
import com.test.sales.watcher.EnvironmentConstants;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InputFileParser
{
    public static FileInfo getFileInfo(Path file) throws IOException
    {
        Stream<String> lines = Files.lines(file);
        FileInfo fileInfo = new FileInfo();

        lines.forEach(element -> {
            String[] columns = element.split(EnvironmentConstants.getInstance().getINPUT_FILE_COLUMN_SEPARATOR());

            if(columns[0].equals(EnvironmentConstants.getInstance().getSELLER_IDENTIFIER()))
            {
                fileInfo.getSellers().add(parseSellerInfo(columns));
            }
            else if(columns[0].equals(EnvironmentConstants.getInstance().getCUSTOMER_IDENTIFIER()))
            {
                fileInfo.getCustomers().add(parseCustomerInfo(columns));
            }
            else if(columns[0].equals(EnvironmentConstants.getInstance().getSALE_IDENTIFIER()))
            {
                fileInfo.getSales().add(parseSaleInfo(columns));
            }
        }
        );

        lines.close();
        return fileInfo;
    }

    private static Seller parseSellerInfo(String[] columns)
    {
        Seller seller = new Seller();
        seller.setCpf(columns[1]);
        seller.setName(columns[2]);
        seller.setSalary(Double.parseDouble(columns[3]));
        return seller;
    }

    private static Customer parseCustomerInfo(String[] columns)
    {
        Customer customer = new Customer();
        customer.setCnpj(columns[1]);
        customer.setName(columns[2]);
        customer.setBusinessArea(columns[3]);
        return customer;
    }

    private static Sale parseSaleInfo(String[] columns)
    {
        Sale sale = new Sale();
        sale.setSaleId(Long.parseLong(columns[1]));
        sale.setItems(parseItemsInfo(columns[2]));
        sale.setSalesMan(columns[3]);
        return sale;
    }

    private static List<Item> parseItemsInfo(String itemsInfo)
    {
        List<Item> items = new ArrayList<>();
        String[] itemData = itemsInfo.split(EnvironmentConstants.getInstance().getITEM_LIST_SEPARATOR());

        for(String data : itemData)
        {
            String[] itemFields = data.split(EnvironmentConstants.getInstance().getITEM_FIELD_SEPARATOR());
            Item item = new Item();
            item.setItemId(Long.parseLong(itemFields[0].replace("[", "")));
            item.setItemQuantity(Integer.parseInt(itemFields[1]));
            item.setItemPrice(Double.parseDouble(itemFields[2].replace("]", "")));
            items.add(item);
        }

        return items;
    }
}