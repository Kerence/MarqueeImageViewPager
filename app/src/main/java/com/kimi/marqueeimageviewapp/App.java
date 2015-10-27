package com.kimi.marqueeimageviewapp;

import android.support.multidex.MultiDexApplication;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by kimi on 2015/10/27.
 */
public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
    }
}
