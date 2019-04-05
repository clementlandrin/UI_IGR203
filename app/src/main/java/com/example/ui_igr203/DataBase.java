package com.example.ui_igr203;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<Step> data;

    public DataBase()
    {
        this.data = new ArrayList<>();
    }

    public List<Step> getData()
    {
        return this.data;
    }

    public void addStep(Step step)
    {
        this.data.add(step);
    }
}
