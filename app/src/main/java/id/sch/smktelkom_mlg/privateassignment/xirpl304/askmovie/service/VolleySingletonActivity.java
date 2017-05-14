package id.sch.smktelkom_mlg.privateassignment.xirpl304.askmovie.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleySingletonActivity {
    private static volatile VolleySingletonActivity mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;

    //private VolleySingleton(Context context)
    //{
    //    if (mInstance != null)
    //    {
    //        throw new RuntimeException(
    //                "Use getInstance() method to get the single instance of this class");
    //    }
    //    mCtx = context;
    //    mRequestQueue = getRequestQueue();
    //}

    public VolleySingletonActivity(Context context) {
        if (mInstance != null) {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class");
        }
        mCtx = context;
        mRequestQueue = getRequestQueue1();
    }

    public static VolleySingletonActivity getInstance(Context context) {
        if (mInstance == null) {
            synchronized (VolleySingleton.class) {
                if (mInstance == null) mInstance = new VolleySingletonActivity(context);
            }
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue1() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue1(Request<T> req) {
        getRequestQueue1().add(req);
    }


}