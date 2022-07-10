package com.MIRSuites.expenselog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity
{
    private TextView categoryTextView;
    private TextView amountTextView;
    private TextView dateTextView;
    private TextView notesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupWidgets();

    }

    private void setupWidgets()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryTextView = (TextView) findViewById(R.id.transactionCategory);
        amountTextView = (TextView) findViewById(R.id.transactionAmount);
        dateTextView = (TextView) findViewById(R.id.transactionDate);
        notesTextView = (TextView) findViewById(R.id.transactionDate);
    }

    @Override
    public boolean onNavigateUp()
    {
        finish();
        return true;
    }

    public void editTapped(View view)
    {
    }

    public void deleteTapped(View view)
    {
    }
}