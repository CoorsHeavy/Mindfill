package com.hudson.mindfill;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Hudson Hughes on 4/24/2016.
 */
public final class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("Lato-Black.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

    }

}
