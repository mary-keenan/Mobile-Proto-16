package mobpro.lesson_3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        //first list item button/prompt -- create listener, opens up AlertDialog when clicked
        final TextView first = (TextView) view.findViewById(R.id.textView);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(first);
            }
        });
        //second list item button/prompt
        final TextView second = (TextView) view.findViewById(R.id.textView1);
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(second);
            }
        });
        //third list item button/prompt
        final TextView third = (TextView) view.findViewById(R.id.textView2);
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(third);
            }
        });
        //fourth list item button/prompt
        final TextView fourth = (TextView) view.findViewById(R.id.textView3);
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(fourth);
            }
        });
        //fifth list item button/prompt
        final TextView fifth = (TextView) view.findViewById(R.id.textView4);
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(fifth);
            }
        });
        //Gives SWITCH button function -- creates listener that waits for a click and switches to Settings when clicked
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Settings();
                ((MainActivity) getActivity()).transitionToFragment(fragment); //calls MainActivity method to switch fragments
            }
        };
        Button btn1 = (Button) view.findViewById(R.id.switchButton);
        btn1.setOnClickListener(listener); //sets up switch button

        return view;
    }

    private void showAlertDialog(final TextView textview) {
        AlertDialog.Builder popup_maker = new AlertDialog.Builder(getContext());
        popup_maker //configure popup
                .setTitle("Do you want to change this list item?")
                .setMessage("Click SET to save your change.")
                .setCancelable(true);
        final EditText field = new EditText(getContext());
        popup_maker.setView(field);
        popup_maker.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        popup_maker.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textview.setText(field.getText().toString());
            }
        });
        AlertDialog popup = popup_maker.create();
        popup.show();
    }
}