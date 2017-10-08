package com.example.sashok.university.helper;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sashok on 4.10.17.
 */

public class ImageUtil {
    public static List<String> returnAllImages(Activity activity) {
        List<String> items = new ArrayList<>();

        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.ImageColumns.DATE_TAKEN
        };
        String image = null;
        String orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";
        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = activity.getContentResolver().query(images, projection, null, null, orderBy);
        if (cursor.moveToFirst()) {
            do {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                image = cursor.getString(column_index);
                items.add(image);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }


}

class ImageFileFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        } else if (isImageFile(file.getAbsolutePath())) {
            return true;
        }
        return false;
    }

    private static boolean isImageFile(String filePath) {
        if (filePath.endsWith(".jpg") || filePath.endsWith(".png"))
        // Add other formats as desired
        {
            return true;
        }
        return false;
    }
}
