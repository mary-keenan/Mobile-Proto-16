package mobpro.lesson_3;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
//    @BindView(R.id.switchButton) Button set_btn;
//    @BindView(R.id.addButton) Button add_btn;
//    @BindView(R.id.app_list) ListView todo_list_view;
    private Context context;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        //ButterKnife.bind(this,view);
        final SQLFeedReader dbHelper = new SQLFeedReader(getContext()); //makes SQLFeedReader to interact with database

        //Gives SWITCH button function -- creates listener that waits for a click and switches to SettingsFragment when clicked
        View.OnClickListener settings_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SettingsFragment();
                ((MainActivity) getActivity()).transitionToFragment(fragment); //calls MainActivity method to switch fragments
            }
        };
        Button set_btn = (Button) view.findViewById(R.id.switchButton);
        set_btn.setOnClickListener(settings_listener); //sets up switch button

        //set up to do list array list and adapter
        ArrayList<TodoItem> todo_array_list = dbHelper.getSQLData(); //creates empty array list 'todo_array_list'
        final custom_list_adapter todo_adapter =
                new custom_list_adapter(getContext(), R.layout.todo_list, todo_array_list); //creates adapter to put 'todo_array_list' info into todo_list.xml
        ListView todo_list_view = (ListView) view.findViewById(R.id.app_list); //gives todo_adapter info to fragment_main's ListView
        todo_list_view.setAdapter(todo_adapter); //sets the adapter to fragment_main's list

        //gives ADD NEW button function -- creates listener that waits for a click and adds a new list item to TodoItem
        View.OnClickListener add_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoItem new_item = new TodoItem("Enter task", null);
                todo_adapter.add(new_item); //uses built-in adapter function to add to list
                dbHelper.addSQLData(new_item); //calls helper's add func
            }
        };
        Button add_btn = (Button) view.findViewById(R.id.addButton);
        add_btn.setOnClickListener(add_listener); //sets up add button

        todo_adapter.notifyDataSetChanged(); //update custom adapter

        return view;
    }


}

