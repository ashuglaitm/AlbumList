package com.ashutosh.album.listener;

/**
 * Listener to notify to caller on download completes
 */
public interface IDownloadServiceListener {

    /**
     * Notify to caller on successfully downloaded and copied in file storage
     */
    void onSuccess(String fileData);

    /**
     * Notify to caller if failure happens during server request and coping data in file storage
     */
    void onFailure(int message);

}
