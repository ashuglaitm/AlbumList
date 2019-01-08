package com.ashutosh.album.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.ashutosh.album.common.Constant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File Utility class
 */
public final class FileUtil {

    private FileUtil() {
        new IllegalStateException("Utility class");
    }

    /**
     * Create file in internal storage folder
     *
     * @param context      context to be used
     * @param dirName      file parent directory
     * @param fileLocation file location
     * @param fileData     file data in form of string
     * @return true if file is created else false
     */
    public static boolean createFile(Context context, String dirName, String fileLocation, String fileData) {
        File dirPath = new File(context.getFilesDir(), dirName);
        if (!dirPath.exists()) {
            dirPath.mkdir();
        }
        File filePath = new File(fileLocation);
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            fileWriter.write(fileData);
            fileWriter.flush();
            return true;
        } catch (IOException e) {
            Log.e(Constant.TAG, e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * Checks is file available in path
     *
     * @param fileLocation fileLocation
     * @return true id exist else false
     */
    public static boolean isFileAvailable(String fileLocation) {
        File file = new File(fileLocation);
        return file.exists();
    }

    /**
     * Read file from location and return content in String format
     *
     * @param fileLocation file location
     * @return file content else blank in case of no data
     */
    @NonNull
    public static String readFile(String fileLocation) throws IOException {
        File filePath = new File(fileLocation);
        if (isFileAvailable(fileLocation)) {
            try (FileReader fileReader = new FileReader(filePath)) {
                BufferedReader reader = new BufferedReader(fileReader);
                return getDataFromFile(reader);
            }
        } else {
            // File is not available
            return "";
        }
    }

    @NonNull
    @VisibleForTesting
    public static String getDataFromFile(BufferedReader reader) throws IOException {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    /**
     * delete file from file location
     *
     * @param fileLocation file location
     */
    public static void deleteFile(String fileLocation) {
        File file = new File(fileLocation);
        if (file.exists()) {
            file.delete();
        }
    }
}
