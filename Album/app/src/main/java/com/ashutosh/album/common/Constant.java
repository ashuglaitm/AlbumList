package com.ashutosh.album.common;

public final class Constant {

    public static final String FILE_NAME = "list.json";
    public static final String DIR_NAME = "album";
    public static final String TAG = "Album";

    private static final int BASE_FAILURE_MESSAGE = 100;
    public static final int DOWNLOAD_SERVICE_ERROR_MESSAGE = BASE_FAILURE_MESSAGE + 1;
    public static final int NO_NETWORK_CONNECTION = BASE_FAILURE_MESSAGE + 2;
    public static final int NO_OFFLINE_FILE_AVAILABLE = BASE_FAILURE_MESSAGE + 3;

}
