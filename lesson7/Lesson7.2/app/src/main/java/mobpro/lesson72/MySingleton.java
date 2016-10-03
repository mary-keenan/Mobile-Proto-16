package mobpro.lesson72;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Starter code given for this homework.
 */
public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) { //checks to make sure there isn't already an instance
            mInstance = new MySingleton(context); //makes one if there isn't
        }
        return mInstance; //returns new or pre-existing queue
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) { //checks to make sure there isn't already a queue
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext()); //makes one if there isn't
        }
        return mRequestQueue; //returns new or pre-existing queue
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}