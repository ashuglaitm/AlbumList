package com.ashutosh.album.listener;

import com.ashutosh.album.model.model.AlbumInformation;

import java.util.List;

/**
 * Interface to notify View Model about data received from model
 */
public interface IContentReceivedListener {
    /**
     * data received from model
     *
     * @param list list of album
     */
    void onDataReceived(List<AlbumInformation> list);

    /**
     * Failure message
     *
     * @param message message
     */
    void onFailure(int message);

}
