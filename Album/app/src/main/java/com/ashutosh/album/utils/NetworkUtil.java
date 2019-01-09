package com.ashutosh.album.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class NetworkUtil {

    private NetworkUtil() {
    }

    /**
     * Return is network available
     *
     * @return true if available else false
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            switch (networkInfo.getType()) {
                case ConnectivityManager.TYPE_MOBILE:
                case ConnectivityManager.TYPE_WIFI:
                    return true;
                default:
                    break;
            }
        }
        return false;
    }
}
