package com.example.sashok.university.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

/**
 * Created by sashok on 4.10.17.
 */

public class ColorUtil {
    public static int getRandomMaterialColor(Context context,String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = context.getResources().getIdentifier("mdcolor_" + typeColor, "array", context.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }
}
