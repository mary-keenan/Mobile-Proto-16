package mobpro.lesson_3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    // This works.... I'm not sure what problem you had. We can investigate later if you still have
    // Butterknife issues
    @BindView(R.id.switchButton) Button set_btn;
    @BindView(R.id.addButton) Button add_btn;
    @BindView(R.id.app_list) ListView todo_list_view;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Lines of code shouldn't be longer than 80 char (theres a ruler in AS!)
        // Also, I love the enthusiastic level of commenting, but it's actually a bit much
        // Most of your lines are well written and I can understand them without the comments.
        // You really only need comments if the lines are too confusing for another dev
        /// to understand. The best code is so well written it needs 0 comments!

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,view);

        //Gives SWITCH button function -- creates listener that waits for a click and switches to Settings when clicked
        View.OnClickListener settings_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Settings();
                ((MainActivity) getActivity()).transitionToFragment(fragment); //calls MainActivity method to switch fragments
            }
        };
//        Button set_btn = (Button) view.findViewById(R.id.switchButton);
        set_btn.setOnClickListener(settings_listener); //sets up switch button

        //set up to do list array list and adapter
        ArrayList<todo_items> todo_array_list = new ArrayList<>(); //creates empty array list 'todo_array_list'
        final custom_list_adapter todo_adapter =
                new custom_list_adapter(getContext(), R.layout.todo_list, todo_array_list); //creates adapter to put 'todo_array_list' info into todo_list.xml
//        ListView todo_list_view = (ListView) view.findViewById(R.id.app_list); //gives todo_adapter info to fragment_main's ListView
        todo_list_view.setAdapter(todo_adapter); //sets the adapter to fragment_main's list

        //gives ADD NEW button function -- creates listener that waits for a click and adds a new list item to todo_items
        View.OnClickListener add_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todo_items new_item = new todo_items("Enter task");
                todo_adapter.add(new_item); //uses built-in adapter function to add to list
            }
        };
//        Button add_btn = (Button) view.findViewById(R.id.addButton);
        add_btn.setOnClickListener(add_listener); //sets up add button

        //we'll start with two to do items called "bob"
        // Dope
        todo_items new_item = new todo_items("bob");
        todo_adapter.add(new_item);
        todo_adapter.add(new_item);

        return view;
    }
}

