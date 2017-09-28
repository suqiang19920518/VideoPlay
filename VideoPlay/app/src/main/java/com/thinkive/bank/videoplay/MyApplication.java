package com.thinkive.bank.videoplay;


import android.app.Application;

import com.thinkive.bank.videoplay.util.CrashHandler;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }
}
