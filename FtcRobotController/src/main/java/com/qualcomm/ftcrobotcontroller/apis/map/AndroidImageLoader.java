package com.qualcomm.ftcrobotcontroller.apis.map;

import android.graphics.BitmapFactory;

public class AndroidImageLoader {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(getResources(), R.id.myimage, options);
    int imageHeight = options.outHeight;
    int imageWidth = options.outWidth;
    String imageType = options.outMimeType;
}
