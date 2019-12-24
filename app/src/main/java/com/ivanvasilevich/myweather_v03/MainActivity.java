package com.ivanvasilevich.myweather_v03;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout todayLayout;
    private LinearLayout monLayout;
    private LinearLayout tueLayout;
    private Button butToday;
    private Button butMonday;
    private Button butTuesday;
    private EditText city;
    private ImageButton settings;
    private final static int REQUEST_CODE = 1;
    //ключ для сохранения нажатия на кнопку для дня(сегодня, понед, вт)
    private static final String KEY_BUTTON = "on";
    //счетчик для сохранения нажатия на кнопку для дня(сегодня, понед, вт)
    private int countButton = 0;
    private String editCity = "Moscow";

    private static final String KEY_WIND = "0";
    private int countWind = 0;
    private static final String KEY_PRESSURE = "1";
    private int countPressure = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String instState;
        if (savedInstanceState == null) instState = "Первый запуск!";
        instState = "Повторный запуск!";
        Toast.makeText(getApplicationContext(), instState + " - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d("log", "onCreate()");

        todayLayout = findViewById(R.id.today_layout);
        monLayout = findViewById(R.id.mon_layout);
        tueLayout = findViewById(R.id.tue_layout);

        setLayoutVisibility(View.VISIBLE, View.GONE, View.GONE);

        ImageView imageWind = findViewById(R.id.imageWind);
        TextView textWind = findViewById(R.id.textWind);
        setSettingsVisibility(imageWind, textWind, countWind);

        ImageView imagePressure = findViewById(R.id.imagePressure);
        TextView textPressure = findViewById(R.id.textPressure);
        setSettingsVisibility(imagePressure, textPressure, countPressure);

        butToday = findViewById(R.id.but_today);
        butToday.setOnClickListener(this);
        butMonday = findViewById(R.id.but_mon);
        butMonday.setOnClickListener(this);
        butTuesday = findViewById(R.id.but_tue);
        butTuesday.setOnClickListener(this);

        city = findViewById(R.id.city);

        settings = findViewById(R.id.settings);
        settings.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_today:
                logCallbackMethods("Weather for today");
                setLayoutVisibility(View.VISIBLE, View.GONE, View.GONE);
                setButtonBackground(R.color.butOn, R.color.butOff, R.color.butOff);
                countButton = 0;
                break;
            case R.id.but_mon:
                logCallbackMethods("Weather for monday");
                setLayoutVisibility(View.GONE, View.VISIBLE, View.GONE);
                setButtonBackground(R.color.butOff, R.color.butOn, R.color.butOff);
                countButton = 1;
                break;
            case R.id.but_tue:
                logCallbackMethods("Weather for tuesday");
                setLayoutVisibility(View.GONE, View.GONE, View.VISIBLE);
                setButtonBackground(R.color.butOff, R.color.butOff, R.color.butOn);
                countButton = 2;
                break;
            case R.id.settings:
                ImageView imageWind = findViewById(R.id.imageWind);
                TextView textWind = findViewById(R.id.textWind);
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                //intent.putExtra("c",imageWind.getVisibility());
                startActivityForResult(intent, REQUEST_CODE);
        }
    }

    private void logCallbackMethods(String callback) {
        Toast.makeText(getApplicationContext(), callback, Toast.LENGTH_SHORT).show();
        Log.d("log", callback);
    }

    private void setLayoutVisibility(int visible, int gone, int gone2) {
        todayLayout.setVisibility(visible);
        monLayout.setVisibility(gone);
        tueLayout.setVisibility(gone2);
    }

    private void setButtonBackground(int p, int p2, int p3) {
        butToday.setBackgroundColor(getResources().getColor(p));
        butMonday.setBackgroundColor(getResources().getColor(p2));
        butTuesday.setBackgroundColor(getResources().getColor(p3));
    }

    //начальное отображение/сокрытие данных
    private void setSettingsVisibility(ImageView image, TextView text, int count) {
        if (count == 0){
        image.setVisibility(View.GONE);
        text.setVisibility(View.GONE);}
        else {image.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);}
    }

    //проверка на видимость при повороте экрана
    private void checkedVisibility(ImageView image, TextView text, int count) {
        if (count == 0) {
            image.setVisibility(View.GONE);
            text.setVisibility(View.GONE);
        } else {
            image.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //checkBox отображает/скрывает параметры ветра
            ImageView imageWind = findViewById(R.id.imageWind);
            TextView textWind = findViewById(R.id.textWind);
            boolean checked = data.getBooleanExtra("a", false);
            if (checked) {
                imageWind.setVisibility(View.VISIBLE);
                textWind.setVisibility(View.VISIBLE);
                countWind = 1;
            } else {
                imageWind.setVisibility(View.GONE);
                textWind.setVisibility(View.GONE);
                countWind = 0;
            }
            //checkBox отображает/скрывает параметры давления
            ImageView imagePressure = findViewById(R.id.imagePressure);
            TextView textPressure = findViewById(R.id.textPressure);
            boolean checked2 = data.getBooleanExtra("b", false);
            if (checked2) {
                imagePressure.setVisibility(View.VISIBLE);
                textPressure.setVisibility(View.VISIBLE);
                countPressure = 1;
            } else {
                imagePressure.setVisibility(View.GONE);
                textPressure.setVisibility(View.GONE);
                countPressure = 0;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onStart() {
        super.onStart();
        logCallbackMethods("onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstState) {
        super.onRestoreInstanceState(saveInstState);
        logCallbackMethods("onRestoreInstanceState()");
        countButton = saveInstState.getInt(KEY_BUTTON);
        if (countButton == 0) {
            onClick(butToday);
        } else if (countButton == 1) {
            onClick(butMonday);
        } else {
            onClick(butTuesday);
        }
        countWind = saveInstState.getInt(KEY_WIND);
        ImageView imageWind = findViewById(R.id.imageWind);
        TextView textWind = findViewById(R.id.textWind);
        checkedVisibility(imageWind, textWind, countWind);
        countPressure = saveInstState.getInt(KEY_PRESSURE);
        ImageView imagePressure = findViewById(R.id.imagePressure);
        TextView textPressure = findViewById(R.id.textPressure);
        checkedVisibility(imagePressure, textPressure, countPressure);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logCallbackMethods("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logCallbackMethods("onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstState) {
        super.onSaveInstanceState(saveInstState);
        logCallbackMethods("onSaveInstanceState()");
        saveInstState.putInt(KEY_BUTTON, countButton);
        saveInstState.putInt(KEY_WIND, countWind);
        saveInstState.putInt(KEY_PRESSURE, countPressure);

    }

    @Override
    protected void onStop() {
        super.onStop();
        logCallbackMethods("onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logCallbackMethods("onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logCallbackMethods("onDestroy()");
    }

}




