package mobpro.lesson_3;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.util.ArrayList;

import mobpro.lesson_3.TodoReaderContract.TodoEntry;

/**
 * Created by mary on 9/22/16. CREATING A SQL DATABASE PART 2
 */

public class SQLFeedReader extends SQLiteOpenHelper{
// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDoReader.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TodoEntry.TABLE_NAME + " (" +
                    TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + //automatically increases _ID for each new row
                    TodoEntry.COLUMN_NAME_TODO_TEXT
//                    + TEXT_TYPE + COMMA_SEP + TodoEntry.COLUMN_NAME_SUBTITLE
                    + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TodoEntry.TABLE_NAME;

    public SQLFeedReader(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
      db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Doesn't like it when you delete this
    }

    public ArrayList<TodoItem> getSQLData() { //returns an array list which is used in the custom adapter
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery("select * from " + TodoEntry.TABLE_NAME, null); //selects all database entries
        resultSet.moveToFirst(); //moves to first entry
        ArrayList<TodoItem> todo_array_list = new ArrayList<>(); //intitializes new array list todo_array_list
        try {
            while (resultSet.moveToNext()) { //while there's a next row, the loop will run
                long itemId = resultSet.getLong( //gets current ID of item
                resultSet.getColumnIndexOrThrow(TodoEntry._ID)
                );
                String itemName = resultSet.getString(
                        resultSet.getColumnIndexOrThrow(TodoEntry.COLUMN_NAME_TODO_TEXT)
                );
                TodoItem new_item = new TodoItem(itemName, (int) itemId); //makes new item with info
                todo_array_list.add(new_item); //adds new to do items to list
            }}
        finally {
            resultSet.close();} //close open database
        return todo_array_list; //return array list
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void deleteSQLData(TodoItem deleted_item) { //deletes row given an id
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(("DELETE FROM " + TodoEntry.TABLE_NAME + " WHERE " + TodoEntry._ID + "=" + deleted_item.getId()));
    }

    public void editSQLData(TodoItem changed_item) { //edits row given an id and new name value
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TodoEntry.COLUMN_NAME_TODO_TEXT, changed_item.getName()); //put new name into values
        db.update(TodoEntry.TABLE_NAME, values, TodoEntry._ID + "=" + changed_item.getId(),null); //replaces name with new name (for changed_item)

    }

    public void addSQLData(TodoItem new_item) { //adds item given name
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TodoEntry.COLUMN_NAME_TODO_TEXT, new_item.getName()); //put new name into values
        long newRowId = db.insert(TodoEntry.TABLE_NAME,null,values); //insert new row with name value
        new_item.setId(newRowId); //set new item's id to row's id
    }
}



