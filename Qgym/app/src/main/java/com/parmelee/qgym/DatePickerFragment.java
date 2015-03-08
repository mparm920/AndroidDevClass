package com.parmelee.qgym;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    Calendar c;
    public DatePickerFragment() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
         super.onCreateDialog(savedInstanceState);
        c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog d = new DatePickerDialog(getActivity(), this, year, month, day);

        return d;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        String newDate = format.format(format.parse((i2 + 1) + "-" + i3 + "-" + i, new ParsePosition(0)));
        ScoreEntryActivity scoreEntry = (ScoreEntryActivity)getActivity();
        scoreEntry.updateDate(newDate);
    }
}
