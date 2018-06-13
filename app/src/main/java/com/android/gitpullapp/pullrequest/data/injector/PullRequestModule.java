package com.android.gitpullapp.pullrequest.data.injector;

import com.android.gitpullapp.common.injector.ApiModule;
import com.android.gitpullapp.common.injector.AppModule;
import com.android.gitpullapp.pullrequest.GitHubService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {ApiModule.class,AppModule.class})
public class PullRequestModule {

    @Provides
    GitHubService provideGitHubService(Retrofit retrofit){
        return retrofit.create(GitHubService.class);
    }
}
