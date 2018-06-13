package com.android.gitpullapp.pullrequest.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PullRequest {
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("node_id")
    @Expose
    public String nodeId;
    @SerializedName("html_url")
    @Expose
    public String htmlUrl;
    @SerializedName("diff_url")
    @Expose
    public String diffUrl;
    @SerializedName("patch_url")
    @Expose
    public String patchUrl;
    @SerializedName("issue_url")
    @Expose
    public String issueUrl;
    @SerializedName("number")
    @Expose
    public Integer number;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("locked")
    @Expose
    public Boolean locked;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("body")
    @Expose
    public String body;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("closed_at")
    @Expose
    public Object closedAt;
    @SerializedName("merged_at")
    @Expose
    public Object mergedAt;
    @SerializedName("merge_commit_sha")
    @Expose
    public String mergeCommitSha;
    @SerializedName("assignee")
    @Expose
    public Object assignee;
    @SerializedName("assignees")
    @Expose
    public List<Object> assignees = null;
    @SerializedName("requested_reviewers")
    @Expose
    public List<Object> requestedReviewers = null;
    @SerializedName("requested_teams")
    @Expose
    public List<Object> requestedTeams = null;
    @SerializedName("commits_url")
    @Expose
    public String commitsUrl;
    @SerializedName("review_comments_url")
    @Expose
    public String reviewCommentsUrl;
    @SerializedName("review_comment_url")
    @Expose
    public String reviewCommentUrl;
    @SerializedName("comments_url")
    @Expose
    public String commentsUrl;
    @SerializedName("statuses_url")
    @Expose
    public String statusesUrl;
    @SerializedName("author_association")
    @Expose
    public String authorAssociation;
}
