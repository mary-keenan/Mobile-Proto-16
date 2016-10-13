package mobpro.lesson72;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    // Again, long lines. I won't take points off, just try to avoid it next time. =)
    // Also, a general thing. Try taking away Debug statements when you're done and you submit.
    // Makes your code look more professional.

    private final String TAG = this.getClass().getName();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main_activity, container, false);

        final Context c = this.getContext();
        Button btn1 = (Button) view.findViewById(R.id.button); //create button they press to fetch stock price
        final EditText input = (EditText) view.findViewById(R.id.input); //create EditText they input the ticker name into
        final TextView priceView = (TextView) view.findViewById(R.id.price); //create TextView to show stock price

        btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = buildSearchURL(input.getText().toString()); //when they click the button, we use 'input' to make a URL
            // Request a string response from the provided URL.
            final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {//response is a raw string (what you see when you enter URL)
                                String editedS = response.substring(3); //take off '// ' so it's in the correct format to be converted to an array
                                Log.d("shortened raw",editedS);
                                JSONArray jArr = new JSONArray(editedS); //create an array jArr using edited raw string
                                String price = extractPriceFromJSON(jArr); //get price from jArr, returns a string (it can only get one price at a time rn)
                                priceView.setText(price); //set TextView to stock price
                            } catch (JSONException e) {
                                e.printStackTrace();}}
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, "A VolleyError occurred."); //prints error out
                            error.printStackTrace();}});
            MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest); //adds request to queue
        }});
        return view;}

    private String buildSearchURL(String companyTicker) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("finance.google.com")
                .appendPath("finance")
                .appendPath("info") //appendPath adds '/'+param to end of url
                .appendQueryParameter("client","iq") //first parameter ('?client=iq')
                .appendQueryParameter("q",companyTicker); //second parameter ('q=companyTicker')
        String url = builder.build().toString(); //and this creates the url ('https.finance.google.com/finance/info?client=iq&q=' + companyTicker)
        return url;
    }

    private String extractPriceFromJSON(JSONArray array) throws JSONException {
        // So this function will return the price of the first element in the array. You could
        // definitely easily modify it so that it returned all prices! :D
        List<String> placeholderList = new ArrayList<String>();
        for(int i=0; i<array.length();i++){ //if there were multiple JSONObjects in the array, this would parse through them (only does it once in this case)
            JSONObject stock = array.getJSONObject(i); //gets JSONObject number i
            String price = stock.getString("l"); //uses key 'l' to find the corresponding value (price) in the JSONObject
            Log.d("in extract loop",price);
            placeholderList.add(price); //adds it to list (which only holds one price rn -- necessary if I later want to get more than one price at a time)
        }
        return placeholderList.get(0); //returns first price because I'm only equipped to receive one price at a time rn (only one price to return anyway)
    }
}