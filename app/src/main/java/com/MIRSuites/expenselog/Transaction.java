package com.MIRSuites.expenselog;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.chrono.*;

public class Transaction
{
    public static ArrayList<Transaction> transactionArrayList = new ArrayList<Transaction>();

    public static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    private String id;          //UID for each event
    private int type;           //0 for income, 1 for expense
    private String category;
    private LocalDate date;          //change type to actual date object
    private float amount;
    private String notes;

    public Transaction(String id, int type, String category, LocalDate date, float amount, String notes)
    {
        this.id = id;
        this.type = type;
        this.category = category;
        this.date = date;
        this.amount = amount;
        this.notes = notes;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public float getAmount()
    {
        return amount;
    }

    public void setAmount(float amount)
    {
        this.amount = amount;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }
}
