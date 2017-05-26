package com.androidutils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class TypeFaceProvider {

    public static final String TYPEFACE_FOLDER = "fonts";
    public static final String TYPEFACE_EXTENSION = ".ttf";

    private static Hashtable<String, Typeface> sTypeFaces = new Hashtable<String, Typeface>(
        4);

    public static Typeface getTypeFace(Context context, String fontFileName) {
        Typeface tempTypeface = sTypeFaces.get(fontFileName);

        if (tempTypeface == null) {
            String fontPath = new StringBuilder(TYPEFACE_FOLDER).append('/').append(fontFileName).append(TYPEFACE_EXTENSION).toString();
            tempTypeface = Typeface.createFromAsset(context.getAssets(), fontPath);
            sTypeFaces.put(fontFileName, tempTypeface);
        }

        return tempTypeface;
    }
}