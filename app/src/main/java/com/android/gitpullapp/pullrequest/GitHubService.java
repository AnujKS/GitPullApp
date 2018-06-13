package com.android.gitpullapp.pullrequest;

import com.android.gitpullapp.pullrequest.data.PullRequest;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GitHubService {
    @Headers({
            "Accept: application/json",
            "content-type: application/json"})
    @GET("{owner}/{repo}/pulls")
    Observable<List<PullRequest>> getPullRequests(@Path(value = "owner") String owner, @Path(value = "repo") String repo);
}
