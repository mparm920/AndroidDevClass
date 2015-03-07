package com.parmelee.qgym;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mparm920 on 8/17/14.
 */
public class ReportsFragment extends ListFragment {
    private Cursor cursor;

    public static ReportsFragment getInstance(Cursor c) {
       ReportsFragment rf = new ReportsFragment();
        rf.cursor = c;
        return  rf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Database", "Reports fragment count " + String.valueOf(cursor.getCount()));

        setListAdapter(new GymnastReportList(getActivity(), cursor, true));
    }

    //@Override
    //public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //   super.onCreateView(inflater, container, savedInstanceState);

        //View view = View.inflate(getActivity(), R.layout.fragment_report, null);

        //return view;
    //}

     class GymnastReportList extends CursorAdapter {

        public GymnastReportList(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);

        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
            View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_report, null);
            //View.inflate(context, R.id.reportFragment, null);

            TextView meetName = (TextView)view.findViewById(R.id.tvMeetName);
            meetName.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Meet.MEET_NAME)));

            TextView meetDate = (TextView)view.findViewById(R.id.tvMeetDate);
            String date = format.format(new Date(cursor.getString(cursor.getColumnIndex(DB_Schema.Meet.DATE))));
            Log.d("Database", "new date " + date);
            meetDate.setText(date);

            TextView scoreVaultVal = (TextView)view.findViewById(R.id.reportsVaultVal);
            scoreVaultVal.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Scores.VAULT)));

            TextView scoreBarsVal = (TextView)view.findViewById(R.id.reportsBarsVal);
            scoreBarsVal.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Scores.BARS)));

            TextView scoreBeamVal = (TextView)view.findViewById(R.id.reportsBeamVal);
            scoreBeamVal.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Scores.BEAM)));

            TextView scoreFloorVal = (TextView)view.findViewById(R.id.reportsFloorVal);
            scoreFloorVal.setText(cursor.getString(cursor.getColumnIndex(DB_Schema.Scores.FLOOR)));

            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

        }
    }
}
