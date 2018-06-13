package com.android.gitpullapp.pullrequest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.gitpullapp.R;
import com.android.gitpullapp.common.BaseActivity;
import com.android.gitpullapp.pullrequest.data.PullRequest;
import com.android.gitpullapp.pullrequest.data.injector.DaggerPullRequestComponent;
import com.android.gitpullapp.pullrequest.data.injector.PullRequestComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullRequestActivity extends BaseActivity implements PullRequestContract.View {

    @BindView(R.id.search)
    AppCompatEditText searchView;

    @BindView(R.id.emptyView)
    AppCompatTextView emptyView;

    @BindView(R.id.recyclerView)
    RecyclerView pullRequestRecyclerView;

    PullRequestPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter=new PullRequestPresenter(this);

        PullRequestComponent pullRequestComponent = DaggerPullRequestComponent
                .builder()
                .build();
        pullRequestComponent.inject(mPresenter);

        mPresenter.start();
    }

    @Override
    public void setupView() {
        mPresenter.getPullRequests("google","dagger");
    }

    @Override
    public void setPresenter(PullRequestContract.Presenter presenter) {

    }

    @Override
    public void setError() {

    }

    @Override
    public void showPullRequests(List<PullRequest> pullRequests) {
        PullRequestRecyclerAdapter recyclerAdapter = new PullRequestRecyclerAdapter(new ArrayList<>(pullRequests));
        pullRequestRecyclerView.setVisibility(View.VISIBLE);
        pullRequestRecyclerView.setNestedScrollingEnabled(false);
        pullRequestRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        pullRequestRecyclerView.setAdapter(recyclerAdapter);
    }
}
