package com.test.sales.parser;

import com.test.sales.entity.Customer;
import com.test.sales.entity.FileInfo;
import com.test.sales.entity.Sale;
import com.test.sales.entity.Seller;
import com.test.sales.watcher.EnvironmentConstants;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
                Seller seller = new Seller();
                fileInfo.getSellers().add(seller);
            }
            else if(columns[0].equals(EnvironmentConstants.getInstance().getCUSTOMER_IDENTIFIER()))
            {
                Customer customer = new Customer();
                fileInfo.getCustomers().add(customer);
            }
            else if(columns[0].equals(EnvironmentConstants.getInstance().getSALE_IDENTIFIER()))
            {
                Sale sale = new Sale();
                fileInfo.getSales().add(sale);
            }
        }
        );

        lines.close();
        return fileInfo;
    }
}
