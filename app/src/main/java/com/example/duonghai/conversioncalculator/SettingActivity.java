package com.example.duonghai.conversioncalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    TextView name;
    String title;
    private String fromSelect = "Meters";
    private String toSelect = "To";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("fSet", fromSelect);
                intent.putExtra("tSet", toSelect);

                setResult(MainActivity.RESULT_SELECT, intent);
                finish();
            }
        });


        Spinner fromSpinner = (Spinner) findViewById(R.id.fromPick);
        Spinner toSpinner = (Spinner) findViewById(R.id.toPick);


        ArrayAdapter<CharSequence> LenAdapter = ArrayAdapter.createFromResource(this,
                R.array.Lengths, android.R.layout.simple_spinner_item);
        LenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> VolAdapter = ArrayAdapter.createFromResource(this,
                R.array.Volumes, android.R.layout.simple_spinner_item);
        VolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Bundle data = getIntent().getExtras();
        String option = data.get("option").toString();

        if(option.equals("Length")) {
            fromSpinner.setAdapter(LenAdapter);
            toSpinner.setAdapter(LenAdapter);
       } else {
            fromSpinner.setAdapter(VolAdapter);
            toSpinner.setAdapter(VolAdapter);
        }


        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                fromSelect= (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                toSelect= (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });
    }



}
