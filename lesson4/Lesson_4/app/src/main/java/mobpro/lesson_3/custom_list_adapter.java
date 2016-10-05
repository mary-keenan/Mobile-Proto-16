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
import butterknife.BindView;

/**
 * Created by mary on 9/18/16.
 */

public class custom_list_adapter extends ArrayAdapter {

    private ArrayList<todo_items> todo_itemses;
    // could delete this
    private Context context;

    public custom_list_adapter(Context context, int simple_list_item_1, ArrayList<todo_items> todo_itemses) {
        super(context, 0, todo_itemses);
        this.todo_itemses = todo_itemses; //lol Android Studio suggested I name this variable todo_itemses so I went with it
        this.context = context; //set custom adapter's array list and context to MainActivityFragment's view
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        todo_items todo_thing = (todo_items) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_list, parent, false);
        }
        final TextView tvName = (TextView) convertView.findViewById(R.id.tv_todo); //create universal text view (for each to do item)
        tvName.setText(todo_thing.name); //set the text of the text view to the name of the to do item

        //put listener on DELETE button, calls delete method if clicked
        View.OnClickListener del_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteItem(position); //passes in position so DeleteItem knows what to delete
            }
        };
        Button del_btn = (Button) convertView.findViewById(R.id.deleteButton);
        del_btn.setOnClickListener(del_listener); //sets up switch button

        //put listener on EDIT button, calls edit method if clicked
        View.OnClickListener edit_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditItem(tvName); //passes in text view so EditItem knows what to edit
            }
        };
        Button edit_btn = (Button) convertView.findViewById(R.id.editButton);
        edit_btn.setOnClickListener(edit_listener); //sets up switch button

        return convertView;
    }

    //Gives DELETE button function -- creates listener that waits for a click and deletes the list item from todo_items
    public void DeleteItem(final int position) {
        todo_itemses.remove(position); //deletes clicked item
        notifyDataSetChanged(); //updates view
        }

    //Gives EDIT button function -- creates listener that waits for a click and edits the selected text view
    public void EditItem(final TextView tvName) {
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
        // You should change the text in the Array and then notify the adapter that it has changed.
        // This works, but it could do something weird when you save your Todos in SQL/S3
        // and then load them back in. In this case, the unedited todo would appear. Let me know
        // If this makes sense, and if not we can chat during class.
        popup_maker.setPositiveButton("Set", new DialogInterface.OnClickListener() { //if they hit SET, it saves their change
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvName.setText(field.getText().toString()); //sets text field to new text
            }
        });
        AlertDialog popup = popup_maker.create(); //make it
        popup.show(); //show it
    }
}
