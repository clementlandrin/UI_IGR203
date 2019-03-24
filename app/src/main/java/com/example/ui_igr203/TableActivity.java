package com.example.ui_igr203;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TableActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton center_table;
    private View four_choice_menu;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_aperitif:
                    return true;
                case R.id.navigation_entree:
                    return true;
                case R.id.navigation_dish:
                    return true;
                case R.id.navigation_dessert:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        center_table = findViewById(R.id.center_table);
        center_table.setOnClickListener(this);

        four_choice_menu = findViewById(R.id.four_choice);
        four_choice_menu.setVisibility(View.INVISIBLE);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.center_table:
                if (four_choice_menu.getVisibility() == View.INVISIBLE)
                {
                    four_choice_menu.setVisibility(View.VISIBLE);
                }
                else if (four_choice_menu.getVisibility() == View.VISIBLE)
                {
                    four_choice_menu.setVisibility(View.INVISIBLE);
                }
        }
    }
}
