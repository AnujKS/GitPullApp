package com.android.gitpullapp.common.injector;

import com.android.gitpullapp.common.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
}
