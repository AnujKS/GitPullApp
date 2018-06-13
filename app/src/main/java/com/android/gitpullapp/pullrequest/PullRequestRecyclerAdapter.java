package com.android.gitpullapp.pullrequest;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.gitpullapp.R;
import com.android.gitpullapp.common.Utils;
import com.android.gitpullapp.pullrequest.data.PullRequest;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullRequestRecyclerAdapter extends RecyclerView.Adapter<PullRequestRecyclerAdapter.ViewHolder> {

    private List<PullRequest> pullRequests;

    public PullRequestRecyclerAdapter(List<PullRequest> pullRequests) {
        this.pullRequests = pullRequests;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pullrequest_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PullRequest pullRequest = pullRequests.get(position);
        holder.nameView.setText(pullRequest.getTitle());
        holder.userAndDate.setText(String.format(Locale.ENGLISH,"on %s by %s", Utils.changeDateFormat(pullRequest.getUpdatedAt()),pullRequest.getUser().getLogin()));
        Glide.with(holder.avatar.getContext())
                .load(pullRequest.getUser().getAvatarUrl())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pull_request_name)
        AppCompatTextView nameView;

        @BindView(R.id.avatar)
        AppCompatImageView avatar;

        @BindView(R.id.user_and_date)
        AppCompatTextView userAndDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}