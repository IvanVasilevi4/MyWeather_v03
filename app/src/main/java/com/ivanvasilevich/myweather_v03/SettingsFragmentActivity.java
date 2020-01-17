package com.ivanvasilevich.myweather_v03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsFragmentActivity extends AppCompatActivity {
    private CheckBox checkBoxWind, checkBoxPressure;
    private static final String KEY_CHECKBOX = "check";
    private static final String KEY_CHECKBOX2 = "check2";
    private int countCheckBox = 0;
    private int countCheckBox2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_fragment);

        checkBoxWind = findViewById(R.id.checkBoxWind);
        checkBoxPressure = findViewById(R.id.checkBoxPressure);

        int countWind = getIntent().getIntExtra("countWind", -1);
        checkBoxWind.setChecked((countWind == 0) ? false : true);
        countCheckBox = (countWind == 0) ? 0 : 1;

        int countPressure = getIntent().getIntExtra("countPressure", -1);
        checkBoxPressure.setChecked((countPressure == 0) ? false : true);
        countCheckBox2 = (countPressure == 0) ? 0 : 1;

        checkBoxWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                countCheckBox = isChecked ? 1 : 0;
            }
        });

        checkBoxPressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                countCheckBox2 = isChecked ? 1 : 0;
            }
        });

        Button done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("a", checkBoxWind.isChecked());
                intent.putExtra("b", checkBoxPressure.isChecked());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button choose = findViewById(R.id.choose);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(SettingsFragmentActivity.this, ListOfCitiesActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onSaveInstanceState(Bundle saveInstState) {
        super.onSaveInstanceState(saveInstState);
        saveInstState.putInt(KEY_CHECKBOX, countCheckBox);
        saveInstState.putInt(KEY_CHECKBOX2, countCheckBox2);
    }

    protected void onRestoreInstanceState(Bundle saveInstState) {
        super.onRestoreInstanceState(saveInstState);
        countCheckBox = saveInstState.getInt(KEY_CHECKBOX);
        checkBoxWind.setChecked((countCheckBox == 0) ? false : true);

        countCheckBox2 = saveInstState.getInt(KEY_CHECKBOX2);
        checkBoxPressure.setChecked((countCheckBox2 == 0) ? false : true);
    }

}
