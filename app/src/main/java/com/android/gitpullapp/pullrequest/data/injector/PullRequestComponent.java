package com.android.gitpullapp.pullrequest.data.injector;

import com.android.gitpullapp.common.injector.ApiModule;
import com.android.gitpullapp.pullrequest.PullRequestPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class,PullRequestModule.class})
public interface PullRequestComponent {
    void inject(PullRequestPresenter presenter);
}
