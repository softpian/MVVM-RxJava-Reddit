package com.softpian.rxmvvmreddit.data.source;

import com.softpian.rxmvvmreddit.data.model.RedditNewsResponse;

import io.reactivex.Single;

public interface NewsDataSource {

    Single<RedditNewsResponse> getTopPosts(String after, String limit);
}
