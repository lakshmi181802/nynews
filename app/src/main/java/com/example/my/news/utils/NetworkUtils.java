package com.example.my.news.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.my.news.NyTimesApplication;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class NetworkUtils {
    /**
     * Checks if the device has a network connection.
     */
    public static boolean isConnected() {
        Context context = NyTimesApplication.getInstance();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
