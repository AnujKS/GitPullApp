package com.android.gitpullapp.pullrequest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.gitpullapp.R;
import com.android.gitpullapp.common.BaseActivity;
import com.android.gitpullapp.pullrequest.data.PullRequest;
import com.android.gitpullapp.pullrequest.data.injector.DaggerPullRequestComponent;
import com.android.gitpullapp.pullrequest.data.injector.PullRequestComponent;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

import static android.text.TextUtils.isEmpty;

public class PullRequestActivity extends BaseActivity implements PullRequestContract.View {

    @BindView(R.id.owner)
    AppCompatEditText ownerView;

    @BindView(R.id.repo)
    AppCompatEditText repoView;

    @BindView(R.id.go)
    AppCompatButton searchButton;

    @BindView(R.id.emptyView)
    AppCompatTextView emptyView;

    @BindView(R.id.recyclerView)
    RecyclerView pullRequestRecyclerView;

    ProgressDialog mDialog;

    PullRequestContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new PullRequestPresenter(this);

        PullRequestComponent pullRequestComponent = DaggerPullRequestComponent
                .builder()
                .build();
        pullRequestComponent.inject((PullRequestPresenter) mPresenter);

        mPresenter.start();
    }

    @Override
    public void setPresenter(PullRequestContract.Presenter presenter) {
        this.mPresenter=presenter;
    }

    @Override
    public void setupView() {

        Observable ownerObservable = RxTextView.textChanges(ownerView).skip(1);
        Observable repoObservable = RxTextView.textChanges(repoView).skip(1);
        Observable.combineLatest(ownerObservable, repoObservable, (owner, repo) -> {
                    Boolean isValid = !isEmpty((CharSequence) owner) && !isEmpty((CharSequence) repo);
                    return isValid;
                })
                .subscribe(new DisposableObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean isValid) {
                        if (isValid) {
                            mPresenter.getPullRequests(ownerView.getText().toString(), repoView.getText().toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) { setError(); }

                    @Override
                    public void onComplete() { }
                });


        searchButton.setOnClickListener(v->{
            if(validateFields()){
                mPresenter.getPullRequests(ownerView.getText().toString(), repoView.getText().toString());
                showDialog();
            }
        });
    }

    boolean validateFields(){
        if(isEmpty(ownerView.getText())){
            ownerView.setError("Please enter repo owner");
            return false;
        }

        if(isEmpty(repoView.getText())){
            ownerView.setError("Please enter repo name");
            return false;
        }

        return true;
    }

    @Override
    public void showDialog() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setMessage("Please wait...");
        }
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void setError() {
        emptyView.setVisibility(View.VISIBLE);
        pullRequestRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideError() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showPullRequests(List<PullRequest> pullRequests) {
        PullRequestRecyclerAdapter recyclerAdapter = new PullRequestRecyclerAdapter(new ArrayList<>(pullRequests));
        pullRequestRecyclerView.setVisibility(View.VISIBLE);
        pullRequestRecyclerView.setNestedScrollingEnabled(false);
        pullRequestRecyclerView.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));
        pullRequestRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        pullRequestRecyclerView.setAdapter(recyclerAdapter);
    }
}
