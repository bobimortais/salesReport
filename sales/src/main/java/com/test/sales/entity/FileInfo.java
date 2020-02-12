package com.test.sales.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileInfo
{
    private List<Customer> customers;

    private List<Seller> sellers;

    private List<Sale> sales;

    public FileInfo()
    {
        customers = new ArrayList<>();
        sellers = new ArrayList<>();
        sales = new ArrayList<>();
    }

    public List<Customer> getCustomers()
    {
        return customers;
    }

    public void setCustomers(List<Customer> customers)
    {
        this.customers = customers;
    }

    public List<Seller> getSellers()
    {
        return sellers;
    }

    public void setSellers(List<Seller> sellers)
    {
        this.sellers = sellers;
    }

    public List<Sale> getSales()
    {
        return sales;
    }

    public void setSales(List<Sale> sales)
    {
        this.sales = sales;
    }

    /**
     * Method to get id of the best sale contained on input file
     * @return long - id of the best sale
     */
    public long getBestSaleId()
    {
        return this.getSales().stream()
                .sorted(
                        (s1, s2) -> Double.valueOf(s2.getItems().stream()
                                .mapToDouble(item -> item.getItemQuantity() * item.getItemPrice()                                             )
                                .sum()).compareTo(s1.getItems().stream()
                                .mapToDouble(item -> item.getItemQuantity() * item.getItemPrice())
                                .sum())
                )
                .collect(Collectors.toList()).get(0).getSaleId();
    }

    /**
     * Method to get worst seller contained on input file
     * @return String - worst seller name
     */
    public String getWorstSeller()
    {
        String worstSeller = this.getSellers().get(0).getName();
        double worstSellerSales = 0;
        int i = 0;

        for(Seller seller : this.getSellers())
        {
            double sellerTotalSalesValue = 0;
            List<Sale> sales = this.getSales().stream().filter(sale -> sale.getSalesMan().equals(seller.getName())).collect(Collectors.toList());

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
}
