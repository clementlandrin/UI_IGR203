package com.example.ui_igr203;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RestaurantRoomActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton table0;
    private ImageButton table1;
    private ImageButton table2;
    private ImageButton table3;
    private ImageButton table4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new
                    File(getFilesDir()+File.separator+"menu.txt")));
            bufferedWriter.write("beer,wine,strong alcohols'salad,hot,cheese,others'meet,fish,vegetarian,cold'coffee,icecream,cheese,fruits");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_restaurant_room);
        table0 = findViewById(R.id.button);
        table1 = findViewById(R.id.button2);
        table2 = findViewById(R.id.button3);
        table3 = findViewById(R.id.button4);
        table4 = findViewById(R.id.button5);
        table0.setOnClickListener(this);
        table1.setOnClickListener(this);
        table2.setOnClickListener(this);
        table3.setOnClickListener(this);
        table4.setOnClickListener(this);
    }

    private void launchTableActivity(String table_id, int chair_number)
    {
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra("table_id", table_id); // send here the id of the table that has been clicked.
        intent.putExtra("chair_number", chair_number); // send here the number of chairs of the table that has been clicked.
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button:
                launchTableActivity("1", 4);
                break;
            case R.id.button2:
                launchTableActivity("2", 2);
                break;
            case R.id.button3:
                launchTableActivity("3", 1);
                break;
            case R.id.button4:
                launchTableActivity("4", 4);
                break;
            case R.id.button5:
                launchTableActivity("5", 2);
                break;
            case R.id.button6:
                launchTableActivity("6", 4);
                break;

        }
    }
}
