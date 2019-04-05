package com.example.ui_igr203;

import java.util.ArrayList;
import java.util.List;

public class Step {
    private String name;

    private List<Category> categoryList;

    public String getName()
    {
        return this.name;
    }
    public Step(String name)
    {
        this.name = name;
        this.categoryList = new ArrayList<>();
    }

    public void addCategory(Category category)
    {
        this.categoryList.add(category);
    }

    public List<Category> getCategoryList()
    {
        return this.categoryList;
    }
}
