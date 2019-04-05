package com.example.ui_igr203;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public String name;

    public List<Item> itemList;

    public Category(String name)
    {
        this.name = name;
        itemList = new ArrayList<>();
    }

    public void addItem(Item item)
    {
        this.itemList.add(item);
    }

    public List<Item> getItemList()
    {
        return itemList;
    }
}
