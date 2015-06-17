package com.github.skykai;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by Administrator on 2014/10/13.
 */
public class DisplayImageOptionsUtils {

	public static final DisplayImageOptions buildDefaultOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.ARGB_8888)
				.cacheInMemory(true)
				.cacheOnDisk(true).build();
		return options;
	}

	public static final DisplayImageOptions buildDefaultOptionsUserface() {

        return buildDefaultOptions();
	}


}
