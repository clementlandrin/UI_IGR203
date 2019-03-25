package com.example.ui_igr203;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TableActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton center_table;
    private View four_choice_menu;
    private TextView choice;
    private boolean started_from_center_table;
    private boolean four_choice_menu_was_displayed;
    private Button category1;
    private Button category2;
    private Button category3;
    private Button category4;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_aperitif:
                    center_table.setColorFilter(getResources().getColor(R.color.aperitifColor),PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_entree:
                    center_table.setColorFilter(getResources().getColor(R.color.entreeColor),PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_dish:
                    center_table.setColorFilter(getResources().getColor(R.color.dishColor),PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_dessert:
                    center_table.setColorFilter(getResources().getColor(R.color.dessertColor),PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        four_choice_menu_was_displayed = false;
        started_from_center_table = false;

        center_table = findViewById(R.id.center_table);
        center_table.setOnClickListener(this);

        choice = findViewById(R.id.choice);
        choice.setVisibility(View.INVISIBLE);
        choice.setTextColor(Color.WHITE);
        choice.setBackgroundColor(Color.BLACK);

        four_choice_menu = findViewById(R.id.four_choice);
        four_choice_menu.setVisibility(View.INVISIBLE);

        category1 = findViewById(R.id.category1);
        category2 = findViewById(R.id.category2);
        category3 = findViewById(R.id.category3);
        category4 = findViewById(R.id.category4);
        category1.setOnClickListener(this);
        category2.setOnClickListener(this);
        category3.setOnClickListener(this);
        category4.setOnClickListener(this);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SpannableString aperitifString = new SpannableString(getResources().getString(R.string.title_aperitif));
        aperitifString.setSpan(new TextAppearanceSpan(this, R.style.AperitifTextAppearance), 0, aperitifString.length(), 0);

        SpannableString entreeString = new SpannableString(getResources().getString(R.string.title_entree));
        entreeString.setSpan(new TextAppearanceSpan(this, R.style.EntreeTextAppearance), 0, entreeString.length(), 0);

        SpannableString dishString = new SpannableString(getResources().getString(R.string.title_dish));
        dishString.setSpan(new TextAppearanceSpan(this, R.style.DishTextAppearance), 0, dishString.length(), 0);

        SpannableString dessertString = new SpannableString(getResources().getString(R.string.title_dessert));
        dessertString.setSpan(new TextAppearanceSpan(this, R.style.DessertTextAppearance), 0, dessertString.length(), 0);

        navigation.getMenu().findItem(R.id.navigation_aperitif).getIcon().setColorFilter(getResources().getColor(R.color.aperitifColor),PorterDuff.Mode.SRC_IN);
        navigation.getMenu().findItem(R.id.navigation_aperitif).setTitle(aperitifString);

        navigation.getMenu().findItem(R.id.navigation_entree).getIcon().setColorFilter(getResources().getColor(R.color.entreeColor),PorterDuff.Mode.SRC_IN);
        navigation.getMenu().findItem(R.id.navigation_entree).setTitle(entreeString);

        navigation.getMenu().findItem(R.id.navigation_dish).getIcon().setColorFilter(getResources().getColor(R.color.dishColor),PorterDuff.Mode.SRC_IN);
        navigation.getMenu().findItem(R.id.navigation_dish).setTitle(dishString);

        navigation.getMenu().findItem(R.id.navigation_dessert).getIcon().setColorFilter(getResources().getColor(R.color.dessertColor),PorterDuff.Mode.SRC_IN);
        navigation.getMenu().findItem(R.id.navigation_dessert).setTitle(dessertString);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        // useless for now as dispatchTouchMethod handle touch input
        switch(v.getId())
        {
            case R.id.center_table:
                break;
            case R.id.category1:
                choice.setText("category1");
                choice.setVisibility(View.VISIBLE);
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
            case R.id.category2:
                choice.setText("category2");
                choice.setVisibility(View.VISIBLE);
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
            case R.id.category3:
                choice.setText("category3");
                choice.setVisibility(View.VISIBLE);
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
            case R.id.category4:
                choice.setText("category4");
                choice.setVisibility(View.VISIBLE);
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private boolean checkIsOnCenterTable(float x, float y) {
        boolean b = false;
        float density = getResources().getDisplayMetrics().density;

        int[] location = new int[2];

        center_table.getLocationOnScreen(location);

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

    private boolean clickOnReleaseButton(float x, float y) {
        int[] location = new int[2];
        float density = getResources().getDisplayMetrics().density;
        center_table.getLocationOnScreen(location);
        float centerX = location[0] + center_table.getWidth()*density/4;
        float centerY = location[1] + center_table.getHeight()*density/4;

        if (x < centerX && y < centerY)
        {
            category1.performClick();
            return true;
        }
        else if (x > centerX && y < centerY)
        {
            category2.performClick();
            return true;
        }
        else if (x < centerX && y > centerY)
        {
            category3.performClick();
            return true;
        }
        else if (x > centerX && y > centerY)
        {
            category4.performClick();
            return true;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        Log.i("onTouchEvent","trigerred");
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                Log.i("onTouchEvent","ACTION_DOWN");

                four_choice_menu_was_displayed = (four_choice_menu.getVisibility() == View.VISIBLE);

                if (checkIsOnCenterTable(event.getX(), event.getY()) && four_choice_menu != null)
                {
                    started_from_center_table = true;
                    four_choice_menu.setVisibility(View.VISIBLE);
                }
                else
                {
                    started_from_center_table = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("onTouchEvent","ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("onTouchEvent","ACTION_UP");
                if (!checkIsOnCenterTable(event.getX(), event.getY()) && four_choice_menu != null)
                {
                    if(started_from_center_table)
                    {
                        if(clickOnReleaseButton(event.getX(), event.getY()))
                        {
                            four_choice_menu.setVisibility(View.INVISIBLE);
                        }
                    }
                }
                else if (checkIsOnCenterTable(event.getX(), event.getY()) && four_choice_menu_was_displayed && four_choice_menu != null)
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
