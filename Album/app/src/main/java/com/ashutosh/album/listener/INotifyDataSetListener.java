package com.ashutosh.album.listener;

import com.ashutosh.album.viewmodel.AlbumListItem;

import java.util.List;

/**
 * Notify view to update the content
 */
public interface INotifyDataSetListener {

    /**
     * Notify to view to update adapter list
     *
     * @param list album item list
     */
    void notifyDataReceived(List<AlbumListItem> list);

    /**
     * show progress bar
     */
    void showProgressBar();

    /**
     * Hide progress bar
     */
    void hideProgressBar();

    /**
     * Show alert dialog to user
     *
     * @param message message to be displayed
     */
    void showAlertDialog(String message);
}
