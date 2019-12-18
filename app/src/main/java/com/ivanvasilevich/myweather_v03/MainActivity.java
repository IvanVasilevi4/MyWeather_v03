package com.ivanvasilevich.myweather_v03;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//Вдохновленный работой Андрея переделал свой проект в третий раз

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout todayLayout;
    private LinearLayout monLayout;
    private LinearLayout tueLayout;
    private Button butToday;
    private Button butMonday;
    private Button butTuesday;
    private EditText city;
    //ключ для сохранения нажатия на кнопку для дня(сегодня, понед, вт)
    private static final String KEY_BUTTON = "on";
    //счетчик для сохранения нажатия на кнопку для дня(сегодня, понед, вт)
    private int countButton = 0;
    private String editCity = "Moscow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //макет настроек.
        // setContentView(R.layout.settings);

        String instState;
        if (savedInstanceState == null) instState = "Первый запуск!";
        instState = "Повторный запуск!";
        Toast.makeText(getApplicationContext(), instState + " - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d("log", "onCreate()");

        todayLayout = findViewById(R.id.today_layout);
        monLayout = findViewById(R.id.mon_layout);
        tueLayout = findViewById(R.id.tue_layout);

        todayLayout.setVisibility(View.VISIBLE);
        monLayout.setVisibility(View.GONE);
        tueLayout.setVisibility(View.GONE);

        butToday = findViewById(R.id.but_today);
        butToday.setOnClickListener(this);
        butMonday = findViewById(R.id.but_mon);
        butMonday.setOnClickListener(this);
        butTuesday = findViewById(R.id.but_tue);
        butTuesday.setOnClickListener(this);

        city = findViewById(R.id.city);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_today:
                Toast.makeText(getApplicationContext(), "Weather for today", Toast.LENGTH_SHORT).show();
                Log.d("log", "Weather for today");
                todayLayout.setVisibility(View.VISIBLE);
                monLayout.setVisibility(View.GONE);
                tueLayout.setVisibility(View.GONE);
                butToday.setBackgroundColor(getResources().getColor(R.color.butOn));
                butMonday.setBackgroundColor(getResources().getColor(R.color.butOff));
                butTuesday.setBackgroundColor(getResources().getColor(R.color.butOff));
                countButton=0;
                break;
            case R.id.but_mon:
                Toast.makeText(getApplicationContext(), "Weather for monday", Toast.LENGTH_SHORT).show();
                Log.d("log", "Weather for monday");
                todayLayout.setVisibility(View.GONE);
                monLayout.setVisibility(View.VISIBLE);
                tueLayout.setVisibility(View.GONE);
                butToday.setBackgroundColor(getResources().getColor(R.color.butOff));
                butMonday.setBackgroundColor(getResources().getColor(R.color.butOn));
                butTuesday.setBackgroundColor(getResources().getColor(R.color.butOff));
                countButton = 1;
                break;
            case R.id.but_tue:
                Toast.makeText(getApplicationContext(), "Weather for tuesday", Toast.LENGTH_SHORT).show();
                Log.d("log", "Weather for tuesday");
                todayLayout.setVisibility(View.GONE);
                monLayout.setVisibility(View.GONE);
                tueLayout.setVisibility(View.VISIBLE);
                butToday.setBackgroundColor(getResources().getColor(R.color.butOff));
                butMonday.setBackgroundColor(getResources().getColor(R.color.butOff));
                butTuesday.setBackgroundColor(getResources().getColor(R.color.butOn));
                countButton = 2;
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.d("log", "onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstState) {
        super.onRestoreInstanceState(saveInstState);
        Toast.makeText(getApplicationContext(), "onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d("log", "onRestoreInstanceState()");
        countButton = saveInstState.getInt(KEY_BUTTON);
        if(countButton == 0) {
            onClick(butToday);
        }
        else if (countButton == 1) {
            onClick(butMonday);
        }
        else {
            onClick(butTuesday);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        Log.d("log", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        Log.d("log", "onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstState) {
        super.onSaveInstanceState(saveInstState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d("log", "onSaveInstanceState()");
        saveInstState.putInt(KEY_BUTTON,countButton);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.d("log", "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        Log.d("log", "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d("log", "onDestroy()");
    }

}




