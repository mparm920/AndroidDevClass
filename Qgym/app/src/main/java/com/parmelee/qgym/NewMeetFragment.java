package com.parmelee.qgym;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parmelee.qgym.R;


public class NewMeetFragment extends DialogFragment implements View.OnClickListener {
    Button btnNewMeet;
    Button btnCancel;
    EditText addNewMeet;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setTitle(R.string.addNewMeet);
        View view = inflater.inflate(R.layout.fragment_new_meet, null);
        builder.setView(view);
        Log.d("Database", "in alertbox");

        btnNewMeet = (Button)view.findViewById(R.id.ok);
        btnNewMeet.setOnClickListener(this);

        btnCancel = (Button)view.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(this);

        addNewMeet = (EditText)view.findViewById(R.id.add_new_meet);

        return builder.create();
    }


    public NewMeetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.ok:
                Log.d("Database", "new meet click event");
                ScoreEntryActivity scoreEntry = (ScoreEntryActivity) getActivity();
                scoreEntry.updateMeets(addNewMeet.getText().toString());
                break;
            default:
                Log.d("Database", String.valueOf(((Button)view).getText()));
                break;
        }
        this.dismiss();
    }
}
