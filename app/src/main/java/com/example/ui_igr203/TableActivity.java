package com.example.ui_igr203;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
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
        // useless for now as dispatchTouchMethod hide
        switch(v.getId())
        {
            case R.id.center_table:
                break;
        }
    }

    private boolean checkIsOnCenterTable(float x, float y) {
        boolean b = false;
        float density = getResources().getDisplayMetrics().density;

        int[] location = new int[2];

        center_table.getLocationOnScreen(location);
        Log.i("check is on table","event::x is : "+Float.toString(x));
        Log.i("check is on table","event::y is : "+Float.toString(y));

        if(center_table != null)
        {
            if (( x > location[0]) && (x < location[0] + center_table.getWidth()*density/2))
            {
                if (( y > location[1]) && (y < location[1] + center_table.getHeight()*density/2))
                {
                    b = true;
                }
            }
        }
        return b;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        Log.i("onTouchEvent","trigerred");
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                Log.i("onTouchEvent","ACTION_DOWN");
                if (checkIsOnCenterTable(event.getX(), event.getY()) && four_choice_menu != null)
                {
                    four_choice_menu.setVisibility(View.VISIBLE);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("onTouchEvent","ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("onTouchEvent","ACTION_UP");
                if (!checkIsOnCenterTable(event.getX(), event.getY()) && four_choice_menu != null)
                {
                    four_choice_menu.setVisibility(View.INVISIBLE);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("onTouchEvent","ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
