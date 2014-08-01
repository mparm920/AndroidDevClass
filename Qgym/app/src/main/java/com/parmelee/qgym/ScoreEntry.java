package com.parmelee.qgym;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


public class ScoreEntry extends ActionBarActivity {
    private DataAccessor da;
    private Spinner spn;
    private Cursor c;
    private ArrayList<String> Meets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_entry);

        try {
            da = DataAccessor.getInstance(getApplicationContext());
            c = da.getMeets();
            c.moveToFirst();
            Meets.add(c.getString(c.getColumnIndex(DB_Schema.Meet.MEET_NAME)));
        } catch (Exception ex) {
            Log.d("Database", ex.toString());
        }

        spn = (Spinner)findViewById(R.id.spn_Meets);
        ArrayAdapter<CharSequence> a = ArrayAdapter.cre(this, this.Meets, android.R.layout.simple_spinner_item);
        a.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn.setAdapter(a);
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