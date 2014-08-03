package com.parmelee.qgym;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class GymnastListFragment extends ListFragment {


    public GymnastListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataAccessor da = new DataAccessor();
        FragmentManager fm = getFragmentManager();

    }

    class GymnastAdaptor extends CursorAdapter {

        GymnastAdaptor(Context c, Cursor cursor) {
            super(c, cursor, true);
        }
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View v = View.inflate(context, R.layout.fragment_gymnast_list, viewGroup);
            TextView gymnastName = (TextView)v.findViewById(R.id.gymnast_name_val);
            gymnastName.setText(cursor.getString(cursor.getColumnIndex("FullName")));

            TextView vaultVal = (TextView)v.findViewById(R.id.vault_val);
            if(cursor.getFloat(cursor.getColumnIndex(DB_Schema.Scores.VAULT)) != 0) {
                vaultVal.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Scores.VAULT)));
            }else{
                vaultVal.setText("0");
            }
            return v;
        }

        @Override
        public void bindView(View v, Context context, Cursor cursor) {
            TextView gymnastName = (TextView)v.findViewById(R.id.gymnast_name_val);
            gymnastName.setText(cursor.getString(cursor.getColumnIndex("FullName")));

            TextView vaultVal = (TextView)v.findViewById(R.id.vault_val);
            if(cursor.getFloat(cursor.getColumnIndex(DB_Schema.Scores.VAULT)) != 0) {
                vaultVal.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Scores.VAULT)));
            }else{
                vaultVal.setText("0");
            }
        }
    }

}
