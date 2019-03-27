package com.example.ui_igr203;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton center_table;
    private View four_choice_menu;
    private Button choice;

    private boolean started_from_center_table;
    private boolean four_choice_menu_was_displayed;
    private boolean choice_selected;

    private Button category1;
    private Button category2;
    private Button category3;
    private Button category4;
    private BottomNavigationView navigation;
    List<String> aperitifCategories;
    List<String> entreeCategories;
    List<String> dishCategories;
    List<String> dessertCategories;

    List<String> currentCategory;

    private void setCategoryText(List<String> categories)
    {
        if(categories == null)
        {
            Log.e("Setting category text","categories is null");
            return;
        }
        if (categories.size()<4)
        {
            category4.setText("");
            category4.setBackgroundColor(Color.WHITE);
            if(categories.size()<3)
            {
                category3.setText("");
                category3.setBackgroundColor(Color.WHITE);
                if(categories.size()<2)
                {
                    category2.setText("");
                    category2.setBackgroundColor(Color.WHITE);
                    if(categories.size()<1)
                    {
                        category1.setText("");
                        category1.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        }
        for(int i = 0; i < categories.size(); i++)
        {
            switch (i)
            {
                case 0:
                    category1.setText(categories.get(0));
                    category1.setBackgroundColor(Color.GRAY);
                    break;
                case 1:
                    category2.setText(categories.get(1));
                    category2.setBackgroundColor(Color.GRAY);
                    break;
                case 2:
                    category3.setText(categories.get(2));
                    category3.setBackgroundColor(Color.GRAY);
                    break;
                case 3:
                    category4.setText(categories.get(3));
                    category4.setBackgroundColor(Color.GRAY);
                    break;
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_aperitif:
                    center_table.setColorFilter(getResources().getColor(R.color.aperitifColor),PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    currentCategory = aperitifCategories;
                    setCategoryText(currentCategory);
                    choice_selected = false;
                    return true;
                case R.id.navigation_entree:
                    center_table.setColorFilter(getResources().getColor(R.color.entreeColor),PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    currentCategory = entreeCategories;
                    setCategoryText(currentCategory);
                    choice_selected = false;
                    return true;
                case R.id.navigation_dish:
                    center_table.setColorFilter(getResources().getColor(R.color.dishColor),PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    currentCategory = dishCategories;
                    setCategoryText(currentCategory);
                    choice_selected = false;
                    return true;
                case R.id.navigation_dessert:
                    center_table.setColorFilter(getResources().getColor(R.color.dessertColor),PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    currentCategory = dessertCategories;
                    setCategoryText(currentCategory);
                    choice_selected = false;
                    return true;
            }
            return false;
        }
    };

    private String ReadMenu()
    {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new
                    File(getFilesDir()+File.separator+"menu.txt")));
            String read;
            StringBuilder builder = new StringBuilder("");

            while((read = bufferedReader.readLine()) != null){
                builder.append(read);
            }
            Log.d("Output", builder.toString());
            bufferedReader.close();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.send_to_kitchen:
                Log.i("onOptionsItemSelected","send_to_kitchen clicked");
                new AlertDialog.Builder(TableActivity.this)
                        .setTitle("Confirm Order")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // send to kitchen
                    Intent intent = new Intent(TableActivity.this, RestaurantRoomActivity.class);
                    startActivity(intent);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cancel
                    }
                }).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.orderingmenu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] split = ReadMenu().split("'");

        if (split.length!=0)
        {
            Log.i("Set category from file", "size is not 0");
            aperitifCategories = new ArrayList<>();
            entreeCategories = new ArrayList<>();
            dishCategories = new ArrayList<>();
            dessertCategories = new ArrayList<>();

            for (int i = 0; i < split.length; i++) {
                String[] splitTmp = split[i].split(",");
                switch (i)
                {
                    case 0:
                        Log.i("Set category from file", "entering case 0");
                        for(int j = 0; j < splitTmp.length; j++)
                        {
                            Log.i("Set from file 0", splitTmp[j]);
                            aperitifCategories.add(splitTmp[j]);
                        }
                        break;
                    case 1:
                        Log.i("Set category from file", "entering case 1");
                        for(int j = 0; j < splitTmp.length; j++)
                        {
                            Log.i("Set from file 1", splitTmp[j]);
                            entreeCategories.add(splitTmp[j]);
                        }
                        break;
                    case 2:
                        Log.i("Set category from file", "entering case 2");
                        for(int j = 0; j < splitTmp.length; j++)
                        {
                            Log.i("Set from file 2", splitTmp[j]);
                            dishCategories.add(splitTmp[j]);
                        }
                        break;
                    case 3:
                        Log.i("Set category from file", "entering case 3");
                        for(int j = 0; j < splitTmp.length; j++)
                        {
                            Log.i("Set from file 3", splitTmp[j]);
                            dessertCategories.add(splitTmp[j]);
                        }
                        break;
                }
            }
        }
        currentCategory = aperitifCategories;

        setContentView(R.layout.activity_table);

        four_choice_menu_was_displayed = false;
        started_from_center_table = false;
        choice_selected = false;

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
        choice.setOnClickListener(this);

        setCategoryText(currentCategory);

        navigation = findViewById(R.id.navigation);
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
                if(currentCategory.size()>0)
                {
                    choice.setText(category1.getText());
                    choice.setVisibility(View.VISIBLE);
                    choice_selected = true;
                }
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
            case R.id.category2:
                if(currentCategory.size()>1)
                {
                    choice.setText(category2.getText());
                    choice.setVisibility(View.VISIBLE);
                    choice_selected = true;
                }
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
            case R.id.category3:
                if(currentCategory.size()>2)
                {
                    choice.setText(category3.getText());
                    choice.setVisibility(View.VISIBLE);
                    choice_selected = true;
                }
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
            case R.id.category4:
                if(currentCategory.size()>3)
                {
                    choice.setText(category4.getText());
                    choice.setVisibility(View.VISIBLE);
                    choice_selected = true;
                }
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
            case R.id.choice:
                choice_selected = false;
                choice.setVisibility(View.INVISIBLE);
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

        if (y > navigation.getY())
        {
            return false;
        }
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
        else if (x > centerX && y > centerY)
        {
            category3.performClick();
            return true;
        }
        else if (x < centerX && y > centerY)
        {
            category4.performClick();
            return true;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!choice_selected){
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
        }
        return super.dispatchTouchEvent(event);
    }
}
