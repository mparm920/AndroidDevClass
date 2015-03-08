package com.parmelee.qgym;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class NotesFragment extends DialogFragment implements View.OnClickListener {
    Button btnSetNotes;
    Button btnCancelNotes;
    EditText newNote;

    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
         super.onCreateDialog(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.notes));

        View view = inflater.inflate(R.layout.fragment_notes, null);
        builder.setView(view);

        btnSetNotes = (Button)view.findViewById(R.id.btn_set_notes);
        btnSetNotes.setOnClickListener(this);

        btnCancelNotes = (Button)view.findViewById(R.id.btn_cancel_notes);
        btnCancelNotes.setOnClickListener(this);

        newNote = (EditText)view.findViewById(R.id.etNewNote);

        return builder.create();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_set_notes:
                ScoreEntryActivity scoreEntry = (ScoreEntryActivity)getActivity();
                scoreEntry.updateNotes(newNote.getText().toString());
            break;
        default:
            Log.d("Database", String.valueOf(((Button) view).getText()));
            break;
        }
        this.dismiss();
    }
}
