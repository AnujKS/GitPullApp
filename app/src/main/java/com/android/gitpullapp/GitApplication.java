package com.android.gitpullapp;

import android.app.Application;

import com.android.gitpullapp.common.injector.ApplicationComponent;
import com.android.gitpullapp.common.injector.DaggerApplicationComponent;

public class GitApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = createApplicationComponent();
    }

    ApplicationComponent getmApplicationComponent() {
        return mApplicationComponent;
    }

    private ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent
                .builder()
                .build();
    }

}
