package com.ashutosh.album.model.service;

import android.util.Log;

import com.ashutosh.album.common.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Connection service class
 */
//Package-private
class ConnectionService {

    private String mUrl;

    /**
     * Open URL connection to download content
     *
     * @return data
     */
    public String openConnection() {
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) getUrl().openConnection();
            httpsURLConnection.connect();
            if (httpsURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()))) {
                    String readLine;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((readLine = reader.readLine()) != null) {
                        stringBuilder.append(readLine);
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (IOException e) {
            Log.e(Constant.TAG, e.getLocalizedMessage());
        }
        return "";
    }

    /**
     * Set server url for connection
     *
     * @param serverUrl url
     */
    public void setURL(final String serverUrl) {
            mUrl = serverUrl;
    }

    private URL getUrl() throws MalformedURLException {
        return new URL(mUrl);
    }
}
