package com.parmelee.qgym;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class ReportsActivity extends ActionBarActivity implements Spinner.OnItemSelectedListener{
    Spinner spnGymnastList;
    DataAccessor da;
    ArrayList<String> gymnastNames;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_reports);

        gymnastNames = new ArrayList<String>();
        da = DataAccessor.getInstance(this);
        Cursor c = da.getGymnast();
        if(c.moveToFirst()) {
            do {
                gymnastNames.add(c.getString(c.getColumnIndex("FullName")));
            }while(c.moveToNext());
        }else{
            Log.d("Database", "Cursor is empty");
        }

        Log.d("Database", gymnastNames.get(0) + " " + gymnastNames.get(1));

        View view = getLayoutInflater().inflate(R.layout.activity_reports, null);
        setContentView(view);
        spnGymnastList = (Spinner)view.findViewById(R.id.spnReports);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gymnastNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnGymnastList.setAdapter(spinnerAdapter);
        spnGymnastList.setOnItemSelectedListener(this);

        Log.d("Database", "Spinner created");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.reports, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        name = gymnastNames.get(i).split(" ")[0];
        ReportsFragment rf = ReportsFragment.getInstance(da.getGymnastReports(name));
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.reportFragment, rf, "GymnastReport");
        ft.commit();

        Log.d("Database", "Report Fragment added");
        Log.d("Database", String.valueOf(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
