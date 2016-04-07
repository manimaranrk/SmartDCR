package com.example.sridhar.smartdcr.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2/6/15.
 * Checking for network connection
 */
public class NetworkConnection {

    private final Context context; //Context for activity

    public NetworkConnection(Context context) {
        this.context = context;
    }

    //If network available it return true else false
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
