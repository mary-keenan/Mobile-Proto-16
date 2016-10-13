package mobpro.lesson_3;

import android.provider.BaseColumns;

/**
 * Created by mary on 9/22/16. CREATING A SQL DATABASE PART 1
 */

//Got most of this code from the android developers page on databases
public final class TodoReaderContract {

    private TodoReaderContract() {
        //private constructor so you don't accidentally instantiate the class(?)
    }
    //defines the table contents
    public static class TodoEntry implements BaseColumns {
        public static final String TABLE_NAME = "toDoList"; //this assignment isn't too important because I didn't hardcode the name/title in the code.
        public static final String COLUMN_NAME_TODO_TEXT = "title"; //the strings can be anything
    }
}
