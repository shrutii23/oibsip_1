package com.example.unitconvertor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText valueEditText;
    private Spinner fromUnitSpinner;
    private Spinner toUnitSpinner;
    private TextView resultTextView;

    private static final String[] UNITS = {
            "Centimeters",
            "Meters",
            "Kilometers",
            "Inches",
            "Feet",
            "Yards"
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueEditText = findViewById(R.id.edit_text_value);
        fromUnitSpinner = findViewById(R.id.spinner_from_unit);
        toUnitSpinner = findViewById(R.id.spinner_to_unit);
        resultTextView = findViewById(R.id.text_view_result);

        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                UNITS
        );
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromUnitSpinner.setAdapter(unitAdapter);
        toUnitSpinner.setAdapter(unitAdapter);

        fromUnitSpinner.setSelection(0);
        toUnitSpinner.setSelection(1);

        findViewById(R.id.button_convert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String valueStr = valueEditText.getText().toString();
        if (valueStr.isEmpty()) {
            resultTextView.setText("Please enter a value");
            return;
        }

        double value = Double.parseDouble(valueStr);
        int fromUnitIndex = fromUnitSpinner.getSelectedItemPosition();
        int toUnitIndex = toUnitSpinner.getSelectedItemPosition();

        double result = convert(value, fromUnitIndex, toUnitIndex);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        resultTextView.setText(decimalFormat.format(value) + " " + UNITS[fromUnitIndex] +
                " = " + decimalFormat.format(result) + " " + UNITS[toUnitIndex]);
    }

    private double convert(double value, int fromUnitIndex, int toUnitIndex) {
        double result = 0.0;

        // Conversion logic
        switch (fromUnitIndex) {
            case 0: // Centimeters
                switch (toUnitIndex) {
                    case 0: // Centimeters to Centimeters
                        result = value;
                        break;
                    case 1: // Centimeters to Meters
                        result = value / 100;
                        break;
                    case 2: // Centimeters to Kilometers
                        result = value / 100000;
                        break;
                    case 3: // Centimeters to Inches
                        result = value * 0.393701;
                        break;
                    case 4: // Centimeters to Feet
                        result = value * 0.0328084;
                        break;
                    case 5: // Centimeters to Yards
                        result = value * 0.0109361;
                        break;
                }
                break;
            case 1: // Meters
                switch (toUnitIndex) {
                    case 0: // Meters to Centimeters
                        result = value * 100;
                        break;
                    case 1: // Meters to Meters
                        result = value;
                        break;
                    case 2: // Meters to Kilometers
                        result = value / 1000;
                        break;
                    case 3: // Meters to Inches
                        result = value * 39.3701;
                        break;
                    case 4: // Meters to Feet
                        result = value * 3.28084;
                        break;
                    case 5: // Meters to Yards
                        result = value * 1.09361;
                        break;
                }
                break;
            case 2: // Kilometers
                switch (toUnitIndex) {
                    case 0: // Kilometers to Centimeters
                        result = value * 100000;
                        break;
                    case 1: // Kilometers to Meters
                        result = value * 1000;
                        break;
                    case 2: // Kilometers to Kilometers
                        result = value;
                        break;
                    case 3: // Kilometers to Inches
                        result = value * 39370.1;
                        break;
                    case 4: // Kilometers to Feet
                        result = value * 3280.84;
                        break;
                    case 5: // Kilometers to Yards
                        result = value * 1093.61;
                        break;
                }
                break;
            case 3: // Inches
                switch (toUnitIndex) {
                    case 0: // Inches to Centimeters
                        result = value * 2.54;
                        break;
                    case 1: // Inches to Meters
                        result = value * 0.0254;
                        break;
                    case 2: // Inches to Kilometers
                        result = value * 0.0000254;
                        break;
                    case 3: // Inches to Inches
                        result = value;
                        break;
                    case 4: // Inches to Feet
                        result = value * 0.0833333;
                        break;
                    case 5: // Inches to Yards
                        result = value * 0.0277778;
                        break;
                }
                break;
            case 4: // Feet
                switch (toUnitIndex) {
                    case 0: // Feet to Centimeters
                        result = value * 30.48;
                        break;
                    case 1: // Feet to Meters
                        result = value * 0.3048;
                        break;
                    case 2: // Feet to Kilometers
                        result = value * 0.0003048;
                        break;
                    case 3: // Feet to Inches
                        result = value * 12;
                        break;
                    case 4: // Feet to Feet
                        result = value;
                        break;
                    case 5: // Feet to Yards
                        result = value * 0.333333;
                        break;
                }
                break;
            case 5: // Yards
                switch (toUnitIndex) {
                    case 0: // Yards to Centimeters
                        result = value * 91.44;
                        break;
                    case 1: // Yards to Meters
                        result = value * 0.9144;
                        break;
                    case 2: // Yards to Kilometers
                        result = value * 0.0009144;
                        break;
                    case 3: // Yards to Inches
                        result = value * 36;
                        break;
                    case 4: // Yards to Feet
                        result = value * 3;
                        break;
                    case 5: // Yards to Yards
                        result = value;
                        break;
                }
                break;
        }

        return result;
    }
}
