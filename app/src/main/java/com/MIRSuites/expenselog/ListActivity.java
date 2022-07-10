package com.MIRSuites.expenselog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

public class ListActivity extends AppCompatActivity {

    private ListView listView;

    private TransactionAdapter transactionAdapter;

    private Button sortButton;
    private Button filterButton;
    private LinearLayout filterView;
    private LinearLayout sortView1;
    private LinearLayout sortView2;

    boolean sortHidden = true;
    boolean filterHidden = true;


    private Boolean ascendingSort = true;
    private String selectedFilter = "all";
    private String currentSearchText = "";
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setupWidgets();
        loadFromDB();

        hideFilter();
        hideSort();
    }

    private void loadFromDB()
    {
        DBManager dbManager = DBManager.instanceOfDB(this);
        Transaction.transactionArrayList = dbManager.fetchTransactionList();
    }

    private void setupWidgets()
    {
        sortButton = (Button) findViewById(R.id.sortButton);
        filterButton = (Button) findViewById(R.id.filterButton);
        filterView = (LinearLayout) findViewById(R.id.filterTabsLayout);
        sortView1 = (LinearLayout) findViewById(R.id.sortTabsLayout);
        sortView2 = (LinearLayout) findViewById(R.id.sortTabsLayout2);

        listView = (ListView) findViewById(R.id.transactionListView);

        //Apply transaction_cell.xml template to listview in ListActivity
        transactionAdapter = new TransactionAdapter(getApplicationContext(), 0, Transaction.transactionArrayList);
        listView.setAdapter(transactionAdapter);

        searchView = (SearchView) findViewById(R.id.transactionSearchView);

    }

    private void hideFilter()
    {
        filterView.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        filterButton.setText("FILTER");
    }

    private void hideSort()
    {
        sortView1.setVisibility(View.GONE);
        sortView2.setVisibility(View.GONE);
        sortButton.setText("SORT");
    }

    private void showFilter()
    {
        filterView.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.VISIBLE);
        filterButton.setText("HIDE");
    }

    private void showSort()
    {
        sortView1.setVisibility(View.VISIBLE);
        sortView2.setVisibility(View.VISIBLE);
        sortButton.setText("HIDE");
    }

    public void showSortTapped(View view)
    {
        if(sortHidden)
        {
            sortHidden = false;
            showSort();
        }
        else
        {
            sortHidden = true;
            hideSort();
        }
    }

    public void showFilterTapped(View view)
    {
        if(filterHidden)
        {
            filterHidden = false;
            showFilter();
        }
        else
        {
            filterHidden = true;
            hideFilter();
        }
    }

    public void allFilterTapped(View view)
    {
    }

    public void creditFilterTapped(View view)
    {
    }

    public void debitFilterTapped(View view)
    {
    }

    public void typeTapped(View view)
    {
    }

    public void alphabeticallyTapped(View view)
    {
    }

    public void dateTapped(View view)
    {
    }

    public void categoryTapped(View view)
    {
    }

    public void amountTapped(View view)
    {
    }

    public void fabTapped(View view)
    {
        Intent createTransaction = new Intent(getApplicationContext(), NewTransactionActivity.class);
        startActivity(createTransaction);
    }
}