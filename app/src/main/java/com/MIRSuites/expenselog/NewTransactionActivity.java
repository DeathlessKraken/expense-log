package com.MIRSuites.expenselog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Calendar;

public class NewTransactionActivity extends AppCompatActivity
{
    private Spinner typeSpinner;
    private Spinner categorySpinner;

    private Button dateButton;

    private EditText amountEditText;
    private EditText notesEditText;

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        setupWidgets();
        setupDatePicker();
    }

    private void setupWidgets()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupTypeSpinner();

        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item));

        dateButton = (Button) findViewById(R.id.datePickerButton);

        amountEditText = (EditText) findViewById(R.id.amountEditText);
        notesEditText = (EditText) findViewById(R.id.notesEditText);
    }

    private void setupTypeSpinner()
    {
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        typeSpinner.setAdapter(ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item));

    }

    private void setupDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = android.R.style.Theme_DeviceDefault_Dialog_MinWidth;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(Instant.now().toEpochMilli());
    }

    private String makeDateString(int day, int month, int year)
    {
        return month + "-" + day + "-" + year;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    @Override
    public boolean onNavigateUp()
    {
        finish();
        return true;
    }

    public void datePickerTapped(View view)
    {
        datePickerDialog.show();
    }

    public void saveTapped(View view)
    {
        String id = String.valueOf(Transaction.transactionArrayList.size());
        int type = typeSpinner.getSelectedItemPosition();
        String category = categorySpinner.getSelectedItem().toString();
        String date = dateButton.getText().toString();
        String amount = amountEditText.getText().toString();
        String notes = notesEditText.getText().toString();

        Transaction newTransaction = new Transaction(id, type, category, LocalDate.parse(date, Transaction.dateTimeFormat), Float.valueOf(amount), notes);
        DBManager dbManager = DBManager.instanceOfDB(this);
        dbManager.addTransaction(newTransaction);
    }

    public void cancelTapped(View view)
    {
        finish();
    }
}