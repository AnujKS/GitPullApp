package com.android.gitpullapp.pullrequest;

import com.android.gitpullapp.pullrequest.data.PullRequest;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class PullRequestPresenter implements PullRequestContract.Presenter {

    @Inject
    GitHubService gitHubService;

    PullRequestContract.View mView;

    public PullRequestPresenter(PullRequestContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.setupView();
    }

    @Override
    public void getPullRequests(String owner, String repo) {
        gitHubService.getPullRequests(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<PullRequest>>() {
                    @Override
                    public void onNext(List<PullRequest> requests) {
                        mView.showPullRequests(requests);
                        mView.dismissDialog();
                        mView.hideError();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setError();
                        mView.dismissDialog();
                    }

                    @Override
                    public void onComplete() { }
                });
    }
}
