package com.MIRSuites.expenselog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    //Instance
    private static DBManager dbManager;

    private static final String TABLE_NAME = "transaction_table";

    //Columns
    private static final String ID_FIELD = "id";
    private static final String TYPE_FIELD = "type";
    private static final String CATEGORY_FIELD = "category";
    private static final String DATE_FIELD = "date";
    private static final String AMOUNT_FIELD = "amount";
    private static final String NOTES_FIELD = "notes";

    //Database Info
    private static final String DB_NAME = "MoneyEventDB";
    private static final int DB_VERSION = 1;
    private static final String ROW_ID = "rowId";

    //Create Table Query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ROW_ID + " INTEGER PRIMARY KEY, "
            + ID_FIELD + " INT, "
            + TYPE_FIELD + " INT, "
            + CATEGORY_FIELD + " TEXT, "
            + DATE_FIELD + " TEXT, "
            + AMOUNT_FIELD + " TEXT, "
            + NOTES_FIELD + " TEXT) ";

    public DBManager(Context context)
    {
        super(context, DB_NAME, null,DB_VERSION);
    }

    public static DBManager instanceOfDB(Context context)
    {
        if(dbManager == null)
            dbManager = new DBManager(context);

        return dbManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addTransaction(Transaction transaction)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, transaction.getId());
        contentValues.put(TYPE_FIELD, transaction.getType());
        contentValues.put(CATEGORY_FIELD, transaction.getCategory());
        contentValues.put(DATE_FIELD, transaction.getDate().toString());
        contentValues.put(AMOUNT_FIELD, String.valueOf(transaction.getAmount()));
        contentValues.put(NOTES_FIELD, transaction.getNotes());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Transaction> fetchTransactionList()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        ArrayList<Transaction> transactionList = new ArrayList<>();

        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(result.getCount() != 0)
        {
            while(result.moveToNext())
            {
                String id = result.getString(1);
                int type = result.getInt(2);
                String category = result.getString(3);
                LocalDate date  = LocalDate.parse(result.getString(4), Transaction.dateTimeFormat);
                float amount = Float.valueOf(result.getString(5));
                String notes = result.getString(6);

                Transaction newTransaction = new Transaction(id, type, category, date, amount, notes);

                transactionList.add(newTransaction);
            }
        }

        return transactionList;
    }

    public int update(Transaction transaction)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, transaction.getId());
        contentValues.put(TYPE_FIELD, transaction.getType());
        contentValues.put(CATEGORY_FIELD, transaction.getCategory());
        contentValues.put(DATE_FIELD, transaction.getDate().toString());
        contentValues.put(AMOUNT_FIELD, String.valueOf(transaction.getAmount()));
        contentValues.put(NOTES_FIELD, transaction.getNotes());

        return sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " = " + transaction.getId(), null);
    }

    public void delete(String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME,ID_FIELD + " = " + id, null);
    }

}
