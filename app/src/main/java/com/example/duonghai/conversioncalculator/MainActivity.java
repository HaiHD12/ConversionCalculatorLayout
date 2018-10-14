package com.example.duonghai.conversioncalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.BreakIterator;


public class MainActivity extends AppCompatActivity {

    EditText fInput, tInput;
    double fTemp, tTemp;
    TextView fLabel, tLabel, name;
    String input, output, f, t, title;
    private String option = "Length";

    public static final int RESULT_SELECT = 1;


    public void Calculate(View view) {
        hideKeyboard(view);
        fInput = (EditText) findViewById(R.id.inputF);
        tInput = (EditText) findViewById(R.id.inputT);
        fLabel = (TextView) findViewById(R.id.FromLabel);
        tLabel = (TextView) findViewById(R.id.ToLabel);
        f = fLabel.getText().toString();
        t = tLabel.getText().toString();

        input = fInput.getText().toString();

        output = tInput.getText().toString();

      if (input.length() != 0) {
            fTemp = Integer.parseInt(input);
            tInput.setText(String.valueOf(convert(f, t, fTemp)));
        } else {
            tTemp = Integer.parseInt(output);
            fInput.setText(String.valueOf(convert(t,f, tTemp)));
        }

    }

    public void Clear(View view) {
        hideKeyboard(view);
        fInput = (EditText) findViewById(R.id.inputF);
        fInput.setText("");

        tInput = (EditText) findViewById(R.id.inputT);
        tInput.setText("");

    }

    public void Mode(View view) {
        hideKeyboard(view);

        name = (TextView) findViewById(R.id.head);
        title = name.getText().toString();
        fLabel = (TextView) findViewById(R.id.FromLabel);
        tLabel = (TextView) findViewById(R.id.ToLabel);

        if (title.equals("Length Converter")) {
            option = "Volume";
            name.setText("Volume Converter");
            fLabel.setText("Gallons");
            tLabel.setText("Liters");
        } else if (title.equals("Volume Converter")) {
            option = "Length";
            name.setText("Length Converter");
            fLabel.setText("Yards");
            tLabel.setText("Meters");

        }
    }

    public double convert(String from, String to, double i) {
        if (from.equals("Yards")){
            if (to.equals("Yards")) {
                return i;
            } else if (to.equals("Meters")) {
                return i * 0.9144;
            } else if (to.equals("Miles")) {
                return i * 0.000568182;
            }

        } else if (from.equals("Meters")){
            if (to.equals("Meters")) {
                return i;
            } else if (to.equals("Yards")) {
                return i * 1.09361;
            } else if (to.equals("Miles")) {
                return i * 0.000621371;
            }

        } else if (from.equals("Miles")) {
            if (to.equals("Miles")) {
                return i;
            } else if (to.equals("Yards")) {
                return i * 1760.0;
            } else if (to.equals("Meters")) {
                return i * 1609.34;
            }
        }

        if (from.equals("Gallons")) {
            if (to.equals("Gallons")) {
                return i;
            } else if (to.equals("Liters")) {
                return i * 3.78541;
            }else if (to.equals("Quarts")) {
                return i * 4.0;
            }

        } else if (from.equals("Liters")) {
            if (to.equals("Liters")) {
                return i;
            } else if (to.equals("Gallons")) {
                return i * 0.264172;
            } else if (to.equals("Quarts")) {
                return i * 1.05669;
            }

        } else if (from.equals("Quarts")) {
            if (to.equals("Quarts")) {
                return i;
            } else if (to.equals("Gallons")) {
                return i * 0.25;
            } else if (to.equals("Liters")) {
                return i * 0.946353;
            }
        }
        return 0.0;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_SELECT) {
            String fSet = data.getStringExtra("fSet");
            String tSet = data.getStringExtra("tSet");

            fLabel = (TextView) findViewById(R.id.FromLabel);
            tLabel = (TextView) findViewById(R.id.ToLabel);

            fLabel.setText(fSet);
            tLabel.setText(tSet);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent i = new Intent(MainActivity.this, SettingActivity.class);
            i.putExtra("option", option);
            startActivityForResult(i, RESULT_SELECT);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fInput = (EditText) findViewById(R.id.inputF);
        tInput = (EditText) findViewById(R.id.inputT);

        fInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    tInput.setText("");
                }
            }
        });

        tInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    fInput.setText("");
                }
            }
        });

    }

    public void hideKeyboard(View view) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}


