package com.test.sales.entity;

import java.util.ArrayList;
import java.util.List;

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
}
