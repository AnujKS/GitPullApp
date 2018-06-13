package com.android.gitpullapp.common.injector;

import com.android.gitpullapp.GitApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private GitApplication mApplication;

    AppModule(GitApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    GitApplication provideApplication() {
        return mApplication;
    }
}
