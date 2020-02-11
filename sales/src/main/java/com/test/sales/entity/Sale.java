package com.test.sales.entity;

import java.util.List;

public class Sale
{
    private long saleId;

    private String salesMan;

    private List<Item> items;

    public long getSaleId()
    {
        return saleId;
    }

    public void setSaleId(long saleId)
    {
        this.saleId = saleId;
    }

    public String getSalesMan()
    {
        return salesMan;
    }

    public void setSalesMan(String salesMan)
    {
        this.salesMan = salesMan;
    }

    public List<Item> getItems()
    {
        return items;
    }

    public void setItems(List<Item> items)
    {
        this.items = items;
    }
}
