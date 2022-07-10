package com.MIRSuites.expenselog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends ArrayAdapter<Transaction>
{

    public TransactionAdapter(Context context, int resource, List<Transaction> transactions)
    {
        super(context, resource, transactions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Transaction transaction = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.transaction_cell, parent, false);
        }

        TextView category = (TextView) convertView.findViewById(R.id.categoryTextView);
        TextView notes = (TextView) convertView.findViewById(R.id.categoryTextView);
        TextView amount = (TextView) convertView.findViewById(R.id.categoryTextView);
        TextView date = (TextView) convertView.findViewById(R.id.categoryTextView);

        category.setText(transaction.getCategory());
        notes.setText(transaction.getNotes());
        amount.setText(new StringBuilder()
                        .append("$")
                        .append(transaction.getAmount())
                        .toString());
        date.setText(transaction.getDate().toString());


        return convertView;
    }
}
