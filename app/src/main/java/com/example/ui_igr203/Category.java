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

    public void setName(String name) { this.name = name; }
    public void setItemList (ArrayList<Item> list) { this.itemList = list; }

    public void addItem(String name, double price) { this.itemList.add(new Item(name, price)); }
    public void addItem(Item item) { this.itemList.add(item); }

    public List<Item> getItemList()
    {
        return itemList;
    }
}
