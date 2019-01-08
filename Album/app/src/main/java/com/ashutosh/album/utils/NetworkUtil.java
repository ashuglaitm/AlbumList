package com.ashutosh.album.utils;

import android.content.Context;
import android.net.ConnectivityManager;


public final class NetworkUtil {

    private NetworkUtil() {
        new IllegalStateException("Utility Class");
    }

    private static ConnectivityManager mConnectivityManager = null;
    /**
     * Return is network available
     *
     * @return true if available else false
     */
    public static boolean isNetworkAvailable(Context context) {
        return isWifiAvailable(context) || isMobileDataAvailable(context);
    }

    private static boolean isWifiAvailable(Context context) {
        return getNetworkService(context).getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
    }

    private static boolean isMobileDataAvailable(Context context) {
        return getNetworkService(context).getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
    }

    private static ConnectivityManager getNetworkService(Context context) {
        if (mConnectivityManager == null) {
            mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return mConnectivityManager;
        }
        return mConnectivityManager;
    }
}
