package com.bignerdranch.android.criminalintent;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import java.util.Date;


public class PickerFragment extends DialogFragment {
    public static final int REQUEST_DATE = 0;
    public static final int REQUEST_TIME = 1;
    private Date mDate;
    FragmentManager fm;

    public static PickerFragment newInstance(Date mDate) {
        Bundle args = new Bundle();
        args.putSerializable(DatePickerFragment.EXTRA_DATE, mDate);

        PickerFragment frag = new PickerFragment();
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getActivity().getSupportFragmentManager();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(DatePickerFragment.EXTRA_DATE);


        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.picker_title);
        dialog.setPositiveButton(R.string.pick_time, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TimePickerFragment tpf = TimePickerFragment.newInstance(mDate);
                tpf.setTargetFragment(PickerFragment.this, REQUEST_TIME);
                tpf.show(fm, "timePicker");
            }
        });

        dialog.setNegativeButton(R.string.pick_date, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatePickerFragment dp =  DatePickerFragment.newInstance(mDate);
                dp.setTargetFragment(PickerFragment.this, REQUEST_DATE);
                dp.show(fm, "datePicker");
            }
        });


        return dialog.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTargetFragment().onActivityResult(requestCode, resultCode, data);
    }
}
