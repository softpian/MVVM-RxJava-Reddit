package com.softpian.rxmvvmreddit.network;


import com.softpian.rxmvvmreddit.data.model.RedditNewsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RxRedditApi {

    @GET("top.json")
    Single<RedditNewsResponse> getTopPosts(@Query("after") String after, @Query("limit") String limit);
}
