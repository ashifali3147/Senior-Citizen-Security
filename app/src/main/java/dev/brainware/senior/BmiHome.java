package dev.brainware.senior;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BmiHome extends AppCompatActivity {
Spinner spnft, spninch;
String[] foot, inch;
ArrayAdapter<String> foot_adapter, inch_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_home);
        spnft = (Spinner) findViewById(R.id.spnft);
        foot = getResources().getStringArray(R.array.foot);
        foot_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, foot);
        spnft.setAdapter(foot_adapter);
//        -------------------------[foot]----------------------
        spninch = (Spinner) findViewById(R.id.spninch);
        inch = getResources().getStringArray(R.array.inch);
        inch_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, inch);
        spnft.setAdapter(foot_adapter);
        spninch.setAdapter(inch_adapter);
    }
}