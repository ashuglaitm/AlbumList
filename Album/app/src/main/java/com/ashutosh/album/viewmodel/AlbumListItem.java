package com.ashutosh.album.viewmodel;

import android.support.annotation.NonNull;

import com.ashutosh.album.model.model.AbstractAlbumInformation;

public class AlbumListItem extends AbstractAlbumInformation implements Comparable<AlbumListItem> {

    public AlbumListItem(String title) {
        super(title);
    }

    @Override
    public int compareTo(@NonNull AlbumListItem albumListItem) {
        return this.mTitle.compareTo(albumListItem.mTitle);
    }


}
