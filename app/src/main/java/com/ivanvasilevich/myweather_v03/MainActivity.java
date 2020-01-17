package com.ivanvasilevich.myweather_v03;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

        setSettingsVisibility((LinearLayout) findViewById(R.id.wind_container), countWind);
        setSettingsVisibility((LinearLayout) findViewById(R.id.pressure_container), countPressure);

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
                //изменил эту строку , когда заменял активити настройки на фрагменты
                Intent intent =
                        new Intent(MainActivity.this, SettingsFragmentActivity.class);
                intent.putExtra("countWind", countWind);
                intent.putExtra("countPressure",countPressure);
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

    //начальное отображение/сокрытие данных+ при повороте экрана
    private void setSettingsVisibility(LinearLayout container, int count) {
        container.setVisibility((count == 0) ? View.GONE : View.VISIBLE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //checkBox отображает/скрывает параметры ветра
            LinearLayout windContainer = findViewById(R.id.wind_container);
            boolean checked = data.getBooleanExtra("a", false);
            windContainer.setVisibility(checked ? View.VISIBLE : View.GONE);
            countWind = checked ? 1 : 0;

            //checkBox отображает/скрывает параметры давления
            LinearLayout pressureContainer = findViewById(R.id.pressure_container);
            boolean checked2 = data.getBooleanExtra("b", false);
            pressureContainer.setVisibility(checked2 ? View.VISIBLE : View.GONE);
            countPressure = checked2 ? 1 : 0;
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
        setSettingsVisibility((LinearLayout) findViewById(R.id.wind_container), countWind);
        countPressure = saveInstState.getInt(KEY_PRESSURE);
        setSettingsVisibility((LinearLayout) findViewById(R.id.pressure_container), countPressure);
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




