package com.android.gitpullapp.pullrequest;

import com.android.gitpullapp.common.BasePresenter;
import com.android.gitpullapp.common.BaseView;
import com.android.gitpullapp.pullrequest.data.PullRequest;

import java.util.List;

public class PullRequestContract {

    public interface View extends BaseView<Presenter>{
        void showPullRequests(List<PullRequest> pullRequests);
        void showDialog();
        void dismissDialog();
    }

    public interface Presenter extends BasePresenter{
        void getPullRequests(String owner,String repo);
    }
}
