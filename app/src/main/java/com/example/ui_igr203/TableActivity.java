package com.example.ui_igr203;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton center_table;
    private int chair_number;
    private View four_choice_menu;
    private Button choice;

    private float initial_choice_x;
    private float initial_choice_y;

    private boolean started_from_center_table;
    private boolean four_choice_menu_was_displayed;
    private boolean choice_selected;
    private boolean dragging;

    private Button category1;
    private Button category2;
    private Button category3;
    private Button category4;
    private BottomNavigationView navigation;
    private List<String> aperitifCategories;
    private List<String> entreeCategories;
    private List<String> dishCategories;
    private List<String> dessertCategories;
    private String currentStep;
    private List<String> currentCategory;
    private int currentColor;

    private void setCategoryText(List<String> categories) {
        if (categories == null) {
            Log.e("Setting category text", "categories is null");
            return;
        }
        if (categories.size() < 4) {
            category4.setText("");
            category4.setBackgroundColor(Color.WHITE);
            if (categories.size() < 3) {
                category3.setText("");
                category3.setBackgroundColor(Color.WHITE);
                if (categories.size() < 2) {
                    category2.setText("");
                    category2.setBackgroundColor(Color.WHITE);
                    if (categories.size() < 1) {
                        category1.setText("");
                        category1.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        }
        for (int i = 0; i < categories.size(); i++) {
            switch (i) {
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

    private void clearChairColor()
    {
        ((ImageView)findViewById(R.id.chair_1)).setColorFilter(Color.BLACK);
        ((ImageView)findViewById(R.id.chair_2)).setColorFilter(Color.BLACK);
        ((ImageView)findViewById(R.id.chair_3)).setColorFilter(Color.BLACK);
        ((ImageView)findViewById(R.id.chair_4)).setColorFilter(Color.BLACK);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_aperitif:
                    center_table.setColorFilter(getResources().getColor(R.color.aperitifColor), PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    currentCategory = aperitifCategories;
                    currentStep = getResources().getString(R.string.title_aperitif);
                    currentColor = getResources().getColor(R.color.aperitifColor);
                    setCategoryText(currentCategory);
                    choice_selected = false;
                    findViewById(R.id.delete_choice).setVisibility(View.INVISIBLE);
                    clearChairColor();
                    return true;
                case R.id.navigation_entree:
                    center_table.setColorFilter(getResources().getColor(R.color.entreeColor), PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    currentCategory = entreeCategories;
                    currentStep = getResources().getString(R.string.title_entree);
                    currentColor = getResources().getColor(R.color.entreeColor);
                    setCategoryText(currentCategory);
                    choice_selected = false;
                    findViewById(R.id.delete_choice).setVisibility(View.INVISIBLE);
                    clearChairColor();
                    return true;
                case R.id.navigation_dish:
                    center_table.setColorFilter(getResources().getColor(R.color.dishColor), PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    currentCategory = dishCategories;
                    currentStep = getResources().getString(R.string.title_dish);
                    currentColor = getResources().getColor(R.color.dishColor);
                    setCategoryText(currentCategory);
                    choice_selected = false;
                    findViewById(R.id.delete_choice).setVisibility(View.INVISIBLE);
                    clearChairColor();
                    return true;
                case R.id.navigation_dessert:
                    center_table.setColorFilter(getResources().getColor(R.color.dessertColor), PorterDuff.Mode.SRC_IN);
                    choice.setVisibility(View.INVISIBLE);
                    currentCategory = dessertCategories;
                    currentStep = getResources().getString(R.string.title_dessert);
                    currentColor = getResources().getColor(R.color.dessertColor);
                    setCategoryText(currentCategory);
                    choice_selected = false;
                    findViewById(R.id.delete_choice).setVisibility(View.INVISIBLE);
                    clearChairColor();
                    return true;
            }
            return false;
        }
    };

    private String ReadMenu() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new
                    File(getFilesDir() + File.separator + "menu.txt")));
            String read;
            StringBuilder builder = new StringBuilder("");

            while ((read = bufferedReader.readLine()) != null) {
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

    private void initializeChairs() {
        if (chair_number < 4) {
            findViewById(R.id.chair_4).setVisibility(View.INVISIBLE);
            if (chair_number < 3) {
                findViewById(R.id.chair_3).setVisibility(View.INVISIBLE);
                if (chair_number < 2) {
                    findViewById(R.id.chair_2).setVisibility(View.INVISIBLE);
                    if (chair_number < 1) {
                        findViewById(R.id.chair_1).setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send_to_kitchen:
                new AlertDialog.Builder(TableActivity.this)
                        .setTitle("Send to kitchen ")
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

        if (split.length != 0) {
            Log.i("Set category from file", "size is not 0");
            aperitifCategories = new ArrayList<>();
            entreeCategories = new ArrayList<>();
            dishCategories = new ArrayList<>();
            dessertCategories = new ArrayList<>();

            for (int i = 0; i < split.length; i++) {
                String[] splitTmp = split[i].split(",");
                switch (i) {
                    case 0:
                        Log.i("Set category from file", "entering case 0");
                        for (int j = 0; j < splitTmp.length; j++) {
                            Log.i("Set from file 0", splitTmp[j]);
                            aperitifCategories.add(splitTmp[j]);
                        }
                        break;
                    case 1:
                        Log.i("Set category from file", "entering case 1");
                        for (int j = 0; j < splitTmp.length; j++) {
                            Log.i("Set from file 1", splitTmp[j]);
                            entreeCategories.add(splitTmp[j]);
                        }
                        break;
                    case 2:
                        Log.i("Set category from file", "entering case 2");
                        for (int j = 0; j < splitTmp.length; j++) {
                            Log.i("Set from file 2", splitTmp[j]);
                            dishCategories.add(splitTmp[j]);
                        }
                        break;
                    case 3:
                        Log.i("Set category from file", "entering case 3");
                        for (int j = 0; j < splitTmp.length; j++) {
                            Log.i("Set from file 3", splitTmp[j]);
                            dessertCategories.add(splitTmp[j]);
                        }
                        break;
                }
            }
        }
        currentCategory = aperitifCategories;
        currentStep = getResources().getString(R.string.title_aperitif);
        currentColor = getResources().getColor(R.color.aperitifColor);

        setContentView(R.layout.activity_table);

        chair_number = getIntent().getIntExtra("chair_number", 0);
        initializeChairs();
        findViewById(R.id.chair_1).setOnClickListener(this);
        findViewById(R.id.chair_2).setOnClickListener(this);
        findViewById(R.id.chair_3).setOnClickListener(this);
        findViewById(R.id.chair_4).setOnClickListener(this);

        four_choice_menu_was_displayed = false;
        started_from_center_table = false;
        choice_selected = false;
        findViewById(R.id.delete_choice).setVisibility(View.INVISIBLE);
        findViewById(R.id.delete_choice).setOnClickListener(this);
        dragging = false;

        center_table = findViewById(R.id.center_table);
        center_table.setOnClickListener(this);

        choice = findViewById(R.id.choice);
        choice.setVisibility(View.INVISIBLE);
        choice.setTextColor(Color.WHITE);
        choice.setBackgroundColor(Color.BLACK);
        choice.setOnClickListener(this);
        choice.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        // Layout has happened here.
                        initial_choice_x = choice.getX();
                        initial_choice_y = choice.getY();
                    }
                });

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

        navigation.getMenu().findItem(R.id.navigation_aperitif).getIcon().setColorFilter(getResources().getColor(R.color.aperitifColor), PorterDuff.Mode.SRC_IN);
        navigation.getMenu().findItem(R.id.navigation_aperitif).setTitle(aperitifString);

        navigation.getMenu().findItem(R.id.navigation_entree).getIcon().setColorFilter(getResources().getColor(R.color.entreeColor), PorterDuff.Mode.SRC_IN);
        navigation.getMenu().findItem(R.id.navigation_entree).setTitle(entreeString);

        navigation.getMenu().findItem(R.id.navigation_dish).getIcon().setColorFilter(getResources().getColor(R.color.dishColor), PorterDuff.Mode.SRC_IN);
        navigation.getMenu().findItem(R.id.navigation_dish).setTitle(dishString);

        navigation.getMenu().findItem(R.id.navigation_dessert).getIcon().setColorFilter(getResources().getColor(R.color.dessertColor), PorterDuff.Mode.SRC_IN);
        navigation.getMenu().findItem(R.id.navigation_dessert).setTitle(dessertString);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (getIntent().getStringExtra("table_id") != null) {
            actionBar.setTitle(getResources().getString(R.string.title_activity_table) + getIntent().getStringExtra("table_id"));
        } else {
            actionBar.setTitle(getResources().getString(R.string.title_activity_table) + getResources().getString(R.string.table_id_unkwnown));
        }
    }

    @Override
    public void onClick(View v) {
        // useless for now as dispatchTouchMethod handle touch input
        switch (v.getId()) {
            case R.id.center_table:
                break;
            case R.id.category1:
                if (currentCategory.size() > 0) {
                    choice.setText(category1.getText());
                    choice.setVisibility(View.VISIBLE);
                    choice_selected = true;
                    findViewById(R.id.delete_choice).setVisibility(View.VISIBLE);
                }
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
            case R.id.category2:
                if (currentCategory.size() > 1) {
                    choice.setText(category2.getText());
                    choice.setVisibility(View.VISIBLE);
                    choice_selected = true;
                    findViewById(R.id.delete_choice).setVisibility(View.VISIBLE);
                }
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
            case R.id.category3:
                if (currentCategory.size() > 2) {
                    choice.setText(category3.getText());
                    choice.setVisibility(View.VISIBLE);
                    choice_selected = true;
                    findViewById(R.id.delete_choice).setVisibility(View.VISIBLE);
                }
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
            case R.id.category4:
                if (currentCategory.size() > 3) {
                    choice.setText(category4.getText());
                    choice.setVisibility(View.VISIBLE);
                    choice_selected = true;
                    findViewById(R.id.delete_choice).setVisibility(View.VISIBLE);
                }
                four_choice_menu.setVisibility(View.INVISIBLE);
                break;
            case R.id.delete_choice:
                choice.setVisibility(View.INVISIBLE);
                findViewById(R.id.delete_choice).setVisibility(View.INVISIBLE);
                choice_selected = false;
            case R.id.chair_1:
                if(!choice_selected && !dragging)
                {
                    ((ImageView)findViewById(R.id.chair_1)).setColorFilter(Color.BLACK);
                    discardChoosenItem(1);
                    // TODO delete the relevant item of the relevant file
                }
                break;
            case R.id.chair_2:
                if(!choice_selected && !dragging)
                {
                    ((ImageView)findViewById(R.id.chair_2)).setColorFilter(Color.BLACK);
                    discardChoosenItem(2);
                    // TODO undo the order for this chair for this step (aperitif, entree, dish, dessert)
                }
                break;
            case R.id.chair_3:
                if(!choice_selected && !dragging)
                {
                    ((ImageView)findViewById(R.id.chair_3)).setColorFilter(Color.BLACK);
                    discardChoosenItem(3);
                    // TODO undo the order for this chair for this step (aperitif, entree, dish, dessert)
                }
                break;
            case R.id.chair_4:
                if(!choice_selected && !dragging)
                {
                    ((ImageView)findViewById(R.id.chair_4)).setColorFilter(Color.BLACK);
                    discardChoosenItem(4);
                    // TODO undo the order for this chair for this step (aperitif, entree, dish, dessert)
                }
                break;
        }
    }

    private void discardChoosenItem(int chair_id)
    {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new
                    File(getFilesDir() + File.separator + "table-"+getIntent().getIntExtra("table_id",0)+"_chair-"+Integer.toString(chair_id)+".txt")));
            String read;
            StringBuilder builder = new StringBuilder("");

            while ((read = bufferedReader.readLine()) != null) {
                builder.append(read);
            }
            Log.d("Output", builder.toString());
            bufferedReader.close();
            if(builder.toString().contains(currentStep+":"))
            {
                //TODO delete currentStep+":"+choosen_item then rewrite the file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean checkIsOnView(float x, float y, View view) {
        float density = getResources().getDisplayMetrics().density;

        int[] location = new int[2];

        view.getLocationOnScreen(location);

        if ((x > location[0]) && (x < location[0] + view.getWidth() * density / 2)) {
            if ((y > location[1]) && (y < location[1] + view.getHeight() * density / 2)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIsOnChair(float x, float y, View view) {

        if (x > view.getLeft() && (x < view.getLeft() + view.getWidth())) {
            if (y > view.getTop() + getSupportActionBar().getHeight() && (y < view.getTop() + view.getHeight() + getSupportActionBar().getHeight())) {
                return true;
            }
        }
        return false;
    }

    private boolean clickOnReleaseButton(float x, float y) {
        int[] location = new int[2];
        float density = getResources().getDisplayMetrics().density;
        center_table.getLocationOnScreen(location);
        float centerX = location[0] + center_table.getWidth() * density / 4;
        float centerY = location[1] + center_table.getHeight() * density / 4;

        if (y > navigation.getY()) {
            return false;
        }
        if (x < centerX && y < centerY) {
            category1.performClick();
            return true;
        } else if (x > centerX && y < centerY) {
            category2.performClick();
            return true;
        } else if (x > centerX && y > centerY) {
            category3.performClick();
            return true;
        } else if (x < centerX && y > centerY) {
            category4.performClick();
            return true;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        Log.i("onTouchEvent", "trigerred");
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!choice_selected) {
                    Log.i("onTouchEvent", "ACTION_DOWN");
                    four_choice_menu_was_displayed = (four_choice_menu.getVisibility() == View.VISIBLE);
                    if (checkIsOnView(event.getX(), event.getY(), center_table) && four_choice_menu != null) {
                        started_from_center_table = true;
                        four_choice_menu.setVisibility(View.VISIBLE);
                    } else {
                        started_from_center_table = false;
                    }
                } else {
                    if (checkIsOnView(event.getX(), event.getY(), choice)) {
                        dragging = true;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("onTouchEvent", "ACTION_MOVE");
                if (dragging) {
                    choice.setX(event.getRawX()-getResources().getDisplayMetrics().density*choice.getWidth()/4);
                    choice.setY(event.getRawY()-getResources().getDisplayMetrics().density*choice.getHeight()/2-getSupportActionBar().getHeight());
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("onTouchEvent", "ACTION_UP");
                if (!choice_selected) {
                    if (!checkIsOnView(event.getX(), event.getY(), center_table) && four_choice_menu != null) {
                        if (started_from_center_table) {
                            if (clickOnReleaseButton(event.getX(), event.getY())) {
                                four_choice_menu.setVisibility(View.INVISIBLE);
                            }
                        }
                    } else if (checkIsOnView(event.getX(), event.getY(), center_table) && four_choice_menu_was_displayed && four_choice_menu != null) {
                        four_choice_menu.setVisibility(View.INVISIBLE);
                    }
                } else if (dragging) {
                    Log.i("onTouch", "start drop");
                    int chairId = releaseDropOnChair(event.getX(), event.getY());
                    Log.i("releaseDropOnChair", "result : " + Integer.toString(chairId));
                    if (chairId != 0) {
                        //TODO add the choice to the order for the good person, for now added in a specific file for this chair of this table.
                        try {
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new
                                    File(getFilesDir()+File.separator+"table-"+getIntent().getIntExtra("table_id",0)+"_chair-"+Integer.toString(chairId)+".txt")));
                            bufferedWriter.write(currentStep+":"+choice.getText().toString()+",");
                            bufferedWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        switch(chairId)
                        {
                            case 1:
                                ((ImageView)findViewById(R.id.chair_1)).setColorFilter(currentColor);
                                break;
                            case 2:
                                ((ImageView)findViewById(R.id.chair_2)).setColorFilter(currentColor);
                                break;
                            case 3:
                                ((ImageView)findViewById(R.id.chair_3)).setColorFilter(currentColor);
                                break;
                            case 4:
                                ((ImageView)findViewById(R.id.chair_4)).setColorFilter(currentColor);
                                break;
                        }
                        choice_selected = false;
                        findViewById(R.id.delete_choice).setVisibility(View.INVISIBLE);
                        choice.setVisibility(View.INVISIBLE);
                        choice.setX(initial_choice_x);
                        choice.setY(initial_choice_y);
                    } else {
                        choice.setVisibility(View.VISIBLE);
                        choice.setX(initial_choice_x);
                        choice.setY(initial_choice_y);
                    }
                    dragging = false;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("onTouchEvent", "ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private int releaseDropOnChair(float x, float y) {
        Log.i("releaseDropOnChair","chair number : "+Integer.toString(chair_number));
        if (chair_number > 0 && checkIsOnChair(x, y, findViewById(R.id.chair_1))) {
            Log.i("releaseDropOnChair","1");
            return 1;
        } else if (chair_number > 1 && checkIsOnChair(x, y, findViewById(R.id.chair_2))) {
            Log.i("releaseDropOnChair","2");
            return 2;
        } else if (chair_number > 2 && checkIsOnChair(x, y, findViewById(R.id.chair_3))) {
            Log.i("releaseDropOnChair","3");
            return 3;
        } else if (chair_number > 3 && checkIsOnChair(x, y, findViewById(R.id.chair_4))) {
            Log.i("releaseDropOnChair","4");
            return 4;
        }
        return 0;
    }
}
