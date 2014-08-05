package com.parmelee.qgym;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;


public class ScoreEntry extends ActionBarActivity {
    private DataAccessor da;
    private Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_entry);

        da = DataAccessor.getInstance(getApplicationContext());

        spn = (Spinner)findViewById(R.id.spn_Meets);
        try {
            SimpleCursorAdapter a = new SimpleCursorAdapter(getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    da.getMeets(),
                    new String[] {"Meet_Name"},
                    new int[] {android.R.id.text1},
                    android.support.v4.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            //a.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spn.setAdapter(a);
        } catch(Exception ex) {
            Log.d("Database", ex.toString());
        }

        Log.d("Database", "starting to add Fragment");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        GymnastListFragment lf = new GymnastListFragment();
        lf.setArgs(da.getGymnast());
        ft.add(R.id.scoreEntryList, lf, "gymnastList");
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.score_entry, menu);
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
}
