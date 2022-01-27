package com.tatvasoftassignment.assignment5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;

import java.text.MessageFormat;

public class EducationalDetailsActivity extends AppCompatActivity {

    private Slider percentageSlider, cgpaSlider;
    private AutoCompleteTextView ac_txtSchool, ac_txtGraduation;
    private Button btnSave;
    private TextView txtPercentageValue, txtCgpaValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_details);

        init_id();
        setSchooling_and_setGraduation();
        setPercentage_and_setCgpa();

        btnSave.setOnClickListener(v -> {
            if (isValid()) {
                Toast.makeText(this, getString(R.string.SignUp_successFully), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void init_id() {
        ac_txtSchool = findViewById(R.id.ac_txtSchool);
        ac_txtGraduation = findViewById(R.id.ac_txtGraduation);
        percentageSlider = findViewById(R.id.percentageSlider);
        cgpaSlider = findViewById(R.id.cgpaSlider);
        txtPercentageValue = findViewById(R.id.txtPercentageValue);
        txtCgpaValue = findViewById(R.id.txtCgpaValue);
        btnSave = findViewById(R.id.btnSave);

    }

    private void setSchooling_and_setGraduation() {
        String[] school = getResources().getStringArray(R.array.schoolArray);
        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<>(this, R.layout.layout_dropdown_item, school);
        ac_txtSchool.setAdapter(schoolAdapter);

        String[] graduation = getResources().getStringArray(R.array.graduationArray);
        ArrayAdapter<String> graduationAdapter = new ArrayAdapter<>(this, R.layout.layout_dropdown_item, graduation);
        ac_txtGraduation.setAdapter(graduationAdapter);
    }

    private void setPercentage_and_setCgpa() {

        percentageSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {

            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }


            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {

                txtPercentageValue.setText(MessageFormat.format("{0}%", slider.getValue()));
            }
        });

        cgpaSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {

            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }


            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {

                txtCgpaValue.setText(MessageFormat.format(" {0}%", slider.getValue()));
            }
        });
    }

    private Boolean isValid() {
        boolean valid = true;

        if (ac_txtSchool.getText().toString().length() == 0) {
            Toast.makeText(this, getString(R.string.select_Graduation), Toast.LENGTH_LONG).show();
            valid = false;
        } else if (ac_txtGraduation.getText().toString().length() == 0) {
            Toast.makeText(this, getString(R.string.select_Graduation), Toast.LENGTH_LONG).show();
            valid = false;
        } else if (txtPercentageValue.getText().toString().length() == 0) {
            Toast.makeText(this, getString(R.string.set_Percentage), Toast.LENGTH_LONG).show();
            valid = false;
        } else if (txtCgpaValue.getText().toString().length() == 0) {
            Toast.makeText(this, getString(R.string.set_cgpa), Toast.LENGTH_LONG).show();
            valid = false;
        }

        return valid;
    }
}