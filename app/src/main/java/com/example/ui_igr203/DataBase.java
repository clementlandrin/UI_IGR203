package com.example.ui_igr203;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataBase {
    private List<Step> menu;

    public DataBase() {
        initAperitifs();
        initEntries();
        initDishes();
        initDeserts();
    }

    public List<Step> getData()
    {
        return this.menu;
    }
    public void addCategory(Step step)
    {
        this.menu.add(step);
    }

    private void initAperitifs() {
        Step aperitifs = new Step("Apéritifs");
        //
        addCategory(aperitifs);
    }

    private void initEntries() {
        Step entries = new Step("Entrées");
        //
        addCategory(entries);
    }

    private void initDishes() {
        Step dishes = new Step("Plats");
        //
        addCategory(dishes);
    }

    private void initDeserts() {
        Step deserts = new Step("Desserts");
        //
        addCategory(deserts);
    }
}
