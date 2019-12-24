package com.ivanvasilevich.myweather_v03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private CheckBox checkBoxWind, checkBoxPressure;
    private static final String KEY_CHECKBOX = "check";
    private static final String KEY_CHECKBOX2 = "check2";
    private int countCheckBox = 0;
    private int countCheckBox2 = 0;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        checkBoxWind = findViewById(R.id.checkBoxWind);
        checkBoxPressure = findViewById(R.id.checkBoxPressure);

        int countWind = getIntent().getIntExtra("countWind",-1);
        if(countWind == 0){
            checkBoxWind.setChecked(false);
            countCheckBox = 0;
        } else {checkBoxWind.setChecked(true);
            countCheckBox = 1;}

        int countPressure = getIntent().getIntExtra("countPressure",-1);
        if(countPressure == 0){
            checkBoxPressure.setChecked(false);
            countCheckBox2 = 0;
        } else {checkBoxPressure.setChecked(true);
            countCheckBox2 = 1;}

        checkBoxWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {countCheckBox = 1;}
                else {countCheckBox = 0;}
            }
        });

        checkBoxPressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {countCheckBox2 = 1;}
                else {countCheckBox2 = 0;}
            }
        });

        Button done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("a",checkBoxWind.isChecked());
                intent.putExtra("b",checkBoxPressure.isChecked());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    protected void onSaveInstanceState(Bundle saveInstState) {
        super.onSaveInstanceState(saveInstState);
        saveInstState.putInt(KEY_CHECKBOX,countCheckBox);
        saveInstState.putInt(KEY_CHECKBOX2,countCheckBox2);
    }

    protected void onRestoreInstanceState(Bundle saveInstState) {
        super.onRestoreInstanceState(saveInstState);
        countCheckBox = saveInstState.getInt(KEY_CHECKBOX);
        if (countCheckBox == 0) checkBoxWind.setChecked(false);
        else checkBoxWind.setChecked(true);
        countCheckBox2 = saveInstState.getInt(KEY_CHECKBOX2);
        if (countCheckBox2 == 0) checkBoxPressure.setChecked(false);
        else checkBoxPressure.setChecked(true);
    }

}
