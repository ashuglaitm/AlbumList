package com.ashutosh.album.model.model;


/**
 * Contains Album Information
 */
public class AlbumInformation extends AbstractAlbumInformation{

    private final int mUserId;
    private final int mId;

    /**
     * Constructor to create AlbumInformation object
     *
     * @param userID user id
     * @param id     id
     * @param title  album title
     */
    public AlbumInformation(int userID, int id, String title) {
        super(title);
        mId = id;
        mUserId = userID;
    }
}
