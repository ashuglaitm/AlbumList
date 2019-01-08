package com.ashutosh.album.model.service;

import android.os.AsyncTask;

import com.ashutosh.album.common.Constant;
import com.ashutosh.album.listener.IDownloadServiceListener;

public class DownloadTask extends AsyncTask<String, String, String> {

    private final IDownloadServiceListener mCallbackListener;

    public DownloadTask(IDownloadServiceListener listener) {
        mCallbackListener = listener;
    }

    @Override
    protected String doInBackground(String[] url) {
        ConnectionService connectionService = new ConnectionService();
        connectionService.setURL(url[0]);
       return connectionService.openConnection();
    }

    @Override
    protected void onProgressUpdate(String... update) {
        super.onProgressUpdate(update);
    }

    @Override
    protected void onPostExecute(String fileData) {
        if (fileData.length() > 0) {
            mCallbackListener.onSuccess(fileData);
        } else {
            mCallbackListener.onFailure(Constant.DOWNLOAD_SERVICE_ERROR_MESSAGE);
        }
    }
}
