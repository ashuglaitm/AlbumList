package com.ashutosh.album.viewmodel;

import android.content.Context;

import com.ashutosh.album.R;
import com.ashutosh.album.common.Constant;
import com.ashutosh.album.listener.IContentReceivedListener;
import com.ashutosh.album.listener.INotifyDataSetListener;
import com.ashutosh.album.model.model.AlbumInformation;
import com.ashutosh.album.model.model.DataManager;
import com.ashutosh.album.utils.NetworkUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewModel implements IContentReceivedListener {

    private final Context mContext;
    private final DataManager mDataManager;
    private final INotifyDataSetListener mViewUpdateListener;
    private static final List<AlbumListItem> mListOfAlbumItem = new ArrayList<>();

    public ViewModel(Context context, INotifyDataSetListener viewUpdateListener) {
        mContext = context;
        mViewUpdateListener = viewUpdateListener;
        mDataManager = new DataManager(this, mContext);
    }

    public void requestAlbumList(Context context) {
        mViewUpdateListener.showProgressBar();
        if (NetworkUtil.isNetworkAvailable(context)) {
            mDataManager.requestDataFromServer();
        } else {
            mDataManager.requestDataFromInternalStorage();
        }
    }

    @Override
    public void onDataReceived(List<AlbumInformation> list) {
        createAlbumListItem(list);
        Collections.sort(mListOfAlbumItem);
        mViewUpdateListener.notifyDataReceived(mListOfAlbumItem);
    }

    private void createAlbumListItem(List<AlbumInformation> list) {
        mListOfAlbumItem.clear();
        for (AlbumInformation albumInformation : list) {
            mListOfAlbumItem.add(new AlbumListItem(albumInformation.getTitle()));
        }
    }

    @Override
    public void onFailure(int message) {
        mViewUpdateListener.hideProgressBar();
        String errorMessage;
        switch (message) {
            case Constant.NO_NETWORK_CONNECTION:
                errorMessage = mContext.getString(R.string.no_network_connection);
                break;
            case Constant.NO_OFFLINE_FILE_AVAILABLE:
                errorMessage = mContext.getString(R.string.no_offline_file);
                break;
            default:
                errorMessage = mContext.getString(R.string.unknown_error_message);
                break;

        }
        mViewUpdateListener.showAlertDialog(errorMessage);
    }
}
