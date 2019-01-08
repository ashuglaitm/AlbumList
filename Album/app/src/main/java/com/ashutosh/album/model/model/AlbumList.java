package com.ashutosh.album.model.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ashutosh.album.common.Constant;
import com.ashutosh.album.utils.FileUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provide AlbumList
 */
//Package-private
class AlbumList {

    private static final String JSON_TAG_USER_ID = "userId";
    private static final String JSON_TAG_ID = "id";
    private static final String JSON_TAG_TITLE = "title";

    /**
     * create album List from internal storage
     *
     * @param fileLocation file location
     * @return list of album
     */
    //package-private
    List<AlbumInformation> createAlbumListFromInternalStorage(String fileLocation) {
        try {
            String albumData = FileUtil.readFile(fileLocation);
            return getAlbumInformationList(albumData);
        } catch (IOException e) {
            Log.e(Constant.TAG, e.getLocalizedMessage());
            return emptyList();
        }
    }

    /**
     * create album List From downloaded ServerData directly
     *
     * @param albumData downloaded data
     * @return list of album
     */
    //package-private
    List<AlbumInformation> createAlbumListFromServerData(String albumData) {
        return getAlbumInformationList(albumData);
    }

    @NonNull
    private List<AlbumInformation> getAlbumInformationList(String albumData) {
        try {
            if (albumData != "") {
                List<AlbumInformation> albumList = new ArrayList<>();
                JSONArray jsonArray = new JSONArray(albumData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int userId = jsonObject.getInt(JSON_TAG_USER_ID);
                    int id = jsonObject.getInt(JSON_TAG_ID);
                    String title = jsonObject.getString(JSON_TAG_TITLE);
                    AlbumInformation albumInformation = new AlbumInformation(userId, id, title);
                    albumList.add(albumInformation);
                }
                return albumList;
            } else {
                return emptyList();
            }
        } catch (JSONException e) {
            Log.e(Constant.TAG, e.getLocalizedMessage());
            return emptyList();
        }
    }

    @NonNull
    private List<AlbumInformation> emptyList() {
        return Collections.emptyList();
    }
}


