package com.example.ui_igr203;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

public class RestaurantRoomActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton table0;
    private ImageButton table1;
    private ImageButton table2;
    private ImageButton table3;
    private ImageButton table4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_room);
        table0 = findViewById(R.id.button);
        table1 = findViewById(R.id.button2);
        table2 = findViewById(R.id.button3);
        table3 = findViewById(R.id.button4);
        table4 = findViewById(R.id.button5);
        table0.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
                Intent intent = new Intent(this, TableActivity.class);
                startActivity(intent);


        }
    }
}
