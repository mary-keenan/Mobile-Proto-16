package mobpro.lesson_3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by mary on 9/18/16.
 */

public class custom_list_adapter extends ArrayAdapter {
//    @BindView(R.id.tv_todo) TextView tvName;
//    @BindView(R.id.editButton) Button edit_btn;
//    @BindView(R.id.deleteButton) Button del_btn;


    private ArrayList<TodoItem> todo_itemses;
    private Context context;

    public custom_list_adapter(Context context, int simple_list_item_1, ArrayList<TodoItem> todo_itemses) {
        super(context, 0, todo_itemses);
        this.todo_itemses = todo_itemses; //lol Android Studio suggested I name this variable todo_itemses so I went with it
        this.context = context; //set custom adapter's array list and context to MainActivityFragment's view
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TodoItem todo_thing = (TodoItem) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_list, parent, false);
        }
        final TextView tvName = (TextView) convertView.findViewById(R.id.tv_todo); //create universal text view (for each to do item)
        tvName.setText(todo_thing.getName()); //set the text of the text view to the name of the to do item

        //put listener on DELETE button, calls delete method if clicked
        View.OnClickListener del_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteItem(todo_thing); //passes in position so DeleteItem knows what to delete
            }
        };
        Button del_btn = (Button) convertView.findViewById(R.id.deleteButton);
        del_btn.setOnClickListener(del_listener); //sets up switch button

        //put listener on EDIT button, calls edit method if clicked
        View.OnClickListener edit_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditItem(todo_thing); //passes in text view so EditItem knows what to edit
            }
        };
        Button edit_btn = (Button) convertView.findViewById(R.id.editButton);
        edit_btn.setOnClickListener(edit_listener); //sets up switch button

        return convertView;
    }

    //Gives DELETE button function -- creates listener that waits for a click and deletes the list item from TodoItem
    public void DeleteItem(TodoItem deleted_item) {
        todo_itemses.remove(deleted_item); //deletes clicked item
        SQLFeedReader dbHelper = new SQLFeedReader(getContext()); //makes SQLFeedReader to interact with database
        dbHelper.deleteSQLData(deleted_item); //calls helper's delete func
        notifyDataSetChanged();
    }

    //Gives EDIT button function -- creates listener that waits for a click and edits the selected text view
    public void EditItem(final TodoItem edited_item) {
        final AlertDialog.Builder popup_maker = new AlertDialog.Builder(getContext());
        popup_maker //configure popup
                .setTitle("Do you want to change this list item?")
                .setMessage("Click SET to save your change.")
                .setCancelable(true);
        final EditText field = new EditText(getContext());
        popup_maker.setView(field);
        popup_maker.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //nothing is saved, they get out of popup
            }
        });
        popup_maker.setPositiveButton("Set", new DialogInterface.OnClickListener() { //if they hit SET, it saves their change
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edited_item.setName(field.getText().toString());
                SQLFeedReader dbHelper = new SQLFeedReader(getContext()); //makes SQLFeedReader to interact with database
                dbHelper.editSQLData(edited_item); //calls helper's edit function
                notifyDataSetChanged();
            }
        });
        AlertDialog popup = popup_maker.create(); //make it
        popup.show(); //show it
    }
}
