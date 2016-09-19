package mobpro.lesson_3;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Settings extends Fragment {
    public Settings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        final View root = view.getRootView(); //this lets me change background color on current fragment
        //Creates BLUE color button (color_button1)
        final Button blue_background = (Button) view.findViewById(R.id.color_button1);
        blue_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.setBackgroundColor(Color.BLUE); //changes current background color
                ((MainActivity) getActivity()).changeBackgroundColor(Color.BLUE); //calls MainActivity to change activity background color
            }
        });
        //Creates YELLOW color button (color_button3)
        final Button yellow_background = (Button) view.findViewById(R.id.color_button3);
        yellow_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.setBackgroundColor(Color.YELLOW);
                ((MainActivity) getActivity()).changeBackgroundColor(Color.YELLOW);
            }
        });
        //Creates RED color button (color_button2)
        final Button red_background = (Button) view.findViewById(R.id.color_button2);
        red_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.setBackgroundColor(Color.RED);
                ((MainActivity) getActivity()).changeBackgroundColor(Color.RED);
            }
        });
        //Gives BACK button function -- creates listener that waits for a click and switches to MainFragment when clicked
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MainActivityFragment();
                ((MainActivity) getActivity()).transitionToFragment(fragment); //calls MainActivity method to switch fragments
            }
        };
        Button btn1 = (Button) view.findViewById(R.id.backButton);
        btn1.setOnClickListener(listener); //sets up back button

        return view;
    }
}
