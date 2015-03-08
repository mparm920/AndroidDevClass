package com.parmelee.qgym;

import android.content.Intent;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;




public class ScoreEntryActivity extends Activity {
    private DataAccessor da;
    private Spinner spn;
    //private Button btnDate;
    //private Button btnNewMeet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_score_entry);
        Log.d("Database", "ScoreEntry.onCreate");
        da = DataAccessor.getInstance(getApplicationContext());

        spn = (Spinner)findViewById(R.id.spn_Meets);
        try {
            SimpleCursorAdapter a = new SimpleCursorAdapter(getApplicationContext(),
                    android.R.layout.simple_spinner_item,
                    da.getMeets(),
                    new String[] {"Meet_Name"},
                    new int[] {android.R.id.text1},
                    android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            //a.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spn.setAdapter(a);

        } catch(Exception ex) {
            Log.d("Database", ex.toString());
        }
        /*Log.d("Database", "Spinner done");
        btnDate = (Button)findViewById(R.id.btn_Date);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateDiag = new DatePickerFragment();
                dateDiag.show(getFragmentManager(), "datePicker");
                Toast.makeText(getApplicationContext(), "Change Date", Toast.LENGTH_LONG).show();

            }
        });
        Log.d("Database", "btn_Date Done");
        btnNewMeet = (Button)findViewById(R.id.btn_newMeet);
        btnNewMeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newMeetDiag = new NewMeetFragment();
                newMeetDiag.show(getFragmentManager(), "newMeet");
                Toast.makeText(getApplicationContext(), "New Meet", Toast.LENGTH_LONG).show();
            }
        });*/
        Log.d("Database", "btn_newMeet Done");

        Log.d("Database", "starting to add Fragment");
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        GymnastListFragment lf = new GymnastListFragment();
        lf.setArgs(da.getGymnast());
        ft.add(R.id.scoreEntryList, lf, "gymnastList");
        ft.commit();
    }

    public void updateDate(String date) {
        Log.d("Database", "from ScoreEntry " + date);
    }

    public void updateMeets(String meetName) {
        Log.d("Database", meetName);
    }

    public void updateNotes(String newNotes) {
        Log.d("Database", newNotes);
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
        switch(item.getItemId()) {
            case R.id.action_settings :
                return true;
            case R.id.action_reports :
                Intent i = new Intent(this, ReportsActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
