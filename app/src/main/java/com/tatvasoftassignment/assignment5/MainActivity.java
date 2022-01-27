package com.tatvasoftassignment.assignment5;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etAddress, etDate;
    private CheckBox checkReading, checkChess, checkDrawing;
    private Button btnNext;
    private AutoCompleteTextView ac_txtCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_id();
        setDateOfBirth();
        setCountry();

        btnNext.setOnClickListener(v -> {
            if (isValid()) {
                Intent intent = new Intent(this, EducationalDetailsActivity.class);
                startActivity(intent);
            }
        });
    }


    private void init_id() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etDate = findViewById(R.id.etDate);
        checkReading = findViewById(R.id.checkReading);
        checkChess = findViewById(R.id.checkChess);
        checkDrawing = findViewById(R.id.checkDrawing);
        btnNext = findViewById(R.id.btnNext);
        ac_txtCountry = findViewById(R.id.ac_txtCountry);
    }

    private void setDateOfBirth() {
        etDate.setOnClickListener(v -> {
            int date, set_month, set_year;
            GregorianCalendar gc = new GregorianCalendar();
            date = gc.get(Calendar.DAY_OF_MONTH);
            set_month = gc.get(Calendar.MONTH);
            set_year = gc.get(Calendar.YEAR);
            DatePickerDialog dpd = new DatePickerDialog(this, (datePicker, year, month, day) -> {
                String launchDate = day + "/" + (month + 1) + "/" + year;
                etDate.setText(launchDate);
            }, set_year, set_month, date);
            dpd.show();
        });

    }

    private void setCountry() {
        String[] country = getResources().getStringArray(R.array.countryArray);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, R.layout.layout_dropdown_item, country);
        ac_txtCountry.setAdapter(countryAdapter);
    }

    private Boolean isValid() {
        boolean valid = true;

        if (etName.getText().toString().length() == 0) {
            Toast.makeText(this, getString(R.string.Enter_Name), Toast.LENGTH_LONG).show();
            valid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            Toast.makeText(this, getString(R.string.Enter_Email), Toast.LENGTH_LONG).show();
            valid = false;
        } else if (etDate.getText().toString().length() == 0) {
            Toast.makeText(this, getString(R.string.select_Date), Toast.LENGTH_LONG).show();
            valid = false;
        } else if (etAddress.getText().toString().length() == 0) {
            Toast.makeText(this, getString(R.string.Enter_Address), Toast.LENGTH_LONG).show();
            valid = false;
        } else if (etPhone.getText().toString().length() != 10) {
            Toast.makeText(this, getString(R.string.Enter_Contact_Number), Toast.LENGTH_LONG).show();
            valid = false;
        } else if (ac_txtCountry.getText().toString().length() == 0) {
            Toast.makeText(this, getString(R.string.select_country), Toast.LENGTH_LONG).show();
            valid = false;
        } else if (!checkReading.isChecked() && !checkChess.isChecked() && !checkDrawing.isChecked()) {
            Toast.makeText(this, getString(R.string.select_at_least_one_hobby), Toast.LENGTH_LONG).show();
            valid = false;
        }


        return valid;
    }
}