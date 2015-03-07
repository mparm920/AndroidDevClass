package com.parmelee.qgym;

import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class GymnastListFragment extends ListFragment {

    private Cursor cursor;

    public GymnastListFragment() {
        Log.d("Database", "GymnastListFrag created");
    }

    public void setArgs(Cursor c) {
        Log.d("Database", "Inside setArgs " + String.valueOf(c.getCount()));
        cursor = c;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CursorAdapter gymnastAdapter = new GymnastAdaptor(getActivity().getApplicationContext(), cursor);
        setListAdapter(gymnastAdapter);
        Log.d("Database", "From list frag " + String.valueOf(cursor.getCount()));

    }


    class GymnastAdaptor extends CursorAdapter {

        GymnastAdaptor(Context c, Cursor cursor) {
            super(c, cursor, true);
            Log.d("Database", "inside GynastAdaptor");
        }
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View v = null;
            try {
                //v = View.inflate(context, R.layout.fragment_gymnast_list, null);
                v = getActivity().getLayoutInflater().inflate(R.layout.fragment_gymnast_list, null);
                TextView gymnastName = (TextView) v.findViewById(R.id.gymnast_name_val);
                gymnastName.setText(cursor.getString(cursor.getColumnIndex("FullName")));

                ImageButton btnNotes = (ImageButton)v.findViewById(R.id.btn_notes);
                btnNotes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NotesFragment notes = new NotesFragment();
                        FragmentManager fm;
                        fm = getActivity().getFragmentManager();
                        notes.show(fm, "Notes");
                        Toast.makeText(getActivity().getApplicationContext(), "Add new Note", Toast.LENGTH_LONG).show();
                    }
                });

                EditText vaultVal = (EditText) v.findViewById(R.id.vault_val);
                if (cursor.getFloat(cursor.getColumnIndex(DB_Schema.Scores.VAULT)) != 0) {
                    vaultVal.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Scores.VAULT)));
                } else {
                    vaultVal.setText("0");
                }

                EditText barVal = (EditText) v.findViewById(R.id.bars_val);
                if (cursor.getFloat(cursor.getColumnIndex(DB_Schema.Scores.BARS)) != 0) {
                    barVal.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Scores.BARS)));
                } else {
                    barVal.setText("0");
                }

                EditText beamVal = (EditText) v.findViewById(R.id.beam_val);
                if (cursor.getFloat(cursor.getColumnIndex(DB_Schema.Scores.BEAM)) != 0) {
                    beamVal.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Scores.BEAM)));
                } else {
                    beamVal.setText("0");
                }

                EditText floorVal = (EditText) v.findViewById(R.id.floor_val);
                if (cursor.getFloat(cursor.getColumnIndex(DB_Schema.Scores.FLOOR)) != 0) {
                    floorVal.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Scores.FLOOR)));
                } else {
                    floorVal.setText("0");
                }

            }catch(Exception ex) {
                Log.d("Database", "GymnastAdaptor.newView " + ex.toString());
            }
            return v;
        }

        @Override
        public void bindView(View v, Context context, Cursor cursor) {
            //try {
                //if (v == null) {
                   // v = View.inflate(context, R.id.fragmentGymnastList, null);
                //}
                //TextView gymnastName = (TextView) v.findViewById(R.id.gymnast_name_val);
                //gymnastName.setText(cursor.getString(cursor.getColumnIndex("FullName")));

                //TextView vaultVal = (TextView) v.findViewById(R.id.vault_val);
                //if (cursor.getFloat(cursor.getColumnIndex(DB_Schema.Scores.VAULT)) != 0) {
                    //vaultVal.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Scores.VAULT)));
                //} else {
                    //vaultVal.setText("0");
                //}
            //}catch (Exception ex) {
                //Log.d("Database", "GymnastAdaptor.newView " + ex.toString());
            //}
        }
    }

}
