package mobpro.lesson_3;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class MainActivity extends AppCompatActivity {
    private int myColor;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //changeBackgroundColor(Color.WHITE); //makes initial background color white (resets it)
        setContentView(R.layout.activity_main);
        transitionToFragment(new MainActivityFragment()); //call main activity fragment

        final SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String default_val = getResources().getString(R.string.saved_background_default);
        int intDefault = Color.parseColor(default_val);
        String key = getString(R.string.saved_background); // 'key'
        int color_hex = sharedPref.getInt(key, intDefault); //THIS LINE ???
        changeBackgroundColor(color_hex);
        myColor = color_hex;
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //switches fragments, new fragment is input
    public void transitionToFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_holder, fragment);
        transaction.commit();
    }

    //changes background color, color number is input
    public void changeBackgroundColor(int COLOR) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(COLOR);
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_background), myColor);
        editor.apply(); // apparently you should use apply instead
    }

    public void setMyColor(int color) {
        this.myColor = color;
    }

}


