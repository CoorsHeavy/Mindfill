package com.hudson.mindfill;

import android.app.Application;
import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Hudson Hughes on 4/24/2016.
 */
public final class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("Lato-Black.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

    }
    public static Context getContext(){
        return context;
    }
}
