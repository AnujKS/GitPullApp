package com.android.gitpullapp.pullrequest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.gitpullapp.R;

public class PullRequestActivity extends AppCompatActivity implements PullRequestContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setPresenter(PullRequestContract.Presenter presenter) {

    }

    @Override
    public void setError() {

    }
}
