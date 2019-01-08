package com.ashutosh.album.model.model;

import android.content.Context;

import com.ashutosh.album.R;
import com.ashutosh.album.common.Constant;
import com.ashutosh.album.listener.IDownloadServiceListener;
import com.ashutosh.album.listener.IContentReceivedListener;
import com.ashutosh.album.model.service.DownloadTask;
import com.ashutosh.album.utils.FileUtil;

import java.io.File;
import java.util.List;

public class DataManager implements IDownloadServiceListener {

    private final IContentReceivedListener mViewModelUpdateListener;
    private final Context mContext;
    private static String sFileLocation;
    private final AlbumList mAlbumList;

    /**
     * DataManager is used to handled model part of application
     *
     * @param listener listener to notify view
     * @param context  context to be used
     */
    public DataManager(IContentReceivedListener listener, Context context) {
        mViewModelUpdateListener = listener;
        mContext = context;
        mAlbumList = new AlbumList();
        sFileLocation = mContext.getFilesDir() + File.separator + Constant.DIR_NAME +
                File.separator + Constant.FILE_NAME;
    }

    /**
     * Request data from server
     */
    public void requestDataFromServer() {
        deleteOfflineFileIfAvailable();
        String url = mContext.getString(R.string.url_album);
        new DownloadTask(this).execute(url);
    }

    /**
     * Request data from Internal Storage
     */
    public void requestDataFromInternalStorage() {
        if (FileUtil.isFileAvailable(sFileLocation)) {
            List<AlbumInformation> albumInformationList = mAlbumList.createAlbumListFromInternalStorage(sFileLocation);
            notifyDataToViewModel(albumInformationList);
        } else {
            mViewModelUpdateListener.onFailure(Constant.NO_NETWORK_CONNECTION);
        }
    }

    @Override
    public void onSuccess(String fileData) {
        boolean isFileCreated = FileUtil.createFile(mContext, Constant.DIR_NAME, sFileLocation, fileData);
        if (isFileCreated) {
            List<AlbumInformation> albumInformationList = mAlbumList.createAlbumListFromServerData(fileData);
            notifyDataToViewModel(albumInformationList);
        } else {
            mViewModelUpdateListener.onFailure(Constant.NO_OFFLINE_FILE_AVAILABLE);
        }
    }

    @Override
    public void onFailure(int message) {
        mViewModelUpdateListener.onFailure(message);
    }

    private void notifyDataToViewModel(List<AlbumInformation> albumInformationList) {
        mViewModelUpdateListener.onDataReceived(albumInformationList);
    }

    private void deleteOfflineFileIfAvailable() {
        if (FileUtil.isFileAvailable(sFileLocation)) {
            FileUtil.deleteFile(sFileLocation);
        }
    }
}
