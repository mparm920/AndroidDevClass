package com.bignerdranch.android.criminalintent;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TimePickerFragment extends DialogFragment {
    private Date mDate;

    public static TimePickerFragment newInstance(Date mDate) {
        Bundle args = new Bundle();
        args.putSerializable(DatePickerFragment.EXTRA_DATE, mDate);

        TimePickerFragment frag = new TimePickerFragment();
        frag.setArguments(args);

        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(DatePickerFragment.EXTRA_DATE);


        Calendar cal = Calendar.getInstance();
        cal.setTime(mDate);
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);

        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_time_picker, null);

        TimePicker tp = (TimePicker)v.findViewById(R.id.dialog_time_timepicker);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                mDate = new GregorianCalendar(year, month, day, i, i2).getTime();
            }
        });

        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
        ad.setTitle(R.string.time_pick_title);
        ad.setPositiveButton(R.string.time_set, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sendResult(Activity.RESULT_OK);
            }
        });
        ad.setView(v);

        return ad.create();
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent i = new Intent();
        i.putExtra(DatePickerFragment.EXTRA_DATE, mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }

}
