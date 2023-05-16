package com.home.retrofitgithub.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubClient {

    @GET("users/{user}/repos")
    Call<List<GitResp>> getListGitRepositories(@Path("user") String user);
}
