package com.ashutosh.album.model.model;

public abstract class AbstractAlbumInformation {

    public final String mTitle;

    protected AbstractAlbumInformation(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }
}
