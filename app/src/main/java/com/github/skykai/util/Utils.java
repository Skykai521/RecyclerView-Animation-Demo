package com.github.skykai.util;

import android.graphics.Bitmap;

import com.github.skykai.App;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sky on 2015/6/18.
 */
public class Utils {

    private static Utils mInstance;

    public static Utils getInst() {
        if (mInstance == null) {
            synchronized (Utils.class) {
                if (mInstance == null) {
                    mInstance = new Utils();
                }
            }
        }
        return mInstance;
    }

    private Utils(){

    }


    public DisplayImageOptions buildDefaultOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .cacheInMemory(true)
                .cacheOnDisk(true).build();
        return options;
    }


    public String readFromAsset(String fileName) {
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = App.getApp().getAssets().open(fileName);
            br = new BufferedReader(new InputStreamReader(is));
            String addonStr = "";
            String line = br.readLine();
            while (line != null) {
                addonStr = addonStr + line;
                line = br.readLine();
            }
            return addonStr;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                br.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
