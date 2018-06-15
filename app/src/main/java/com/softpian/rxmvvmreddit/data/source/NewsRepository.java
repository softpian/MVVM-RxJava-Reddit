package com.softpian.rxmvvmreddit.data.source;

import com.softpian.rxmvvmreddit.data.model.RedditNewsResponse;
import com.softpian.rxmvvmreddit.data.source.remote.NewsRemoteDataSource;

import io.reactivex.Single;

public class NewsRepository implements NewsDataSource {

    private static NewsRepository INSTANCE;
    private final NewsRemoteDataSource mNewsRemoteDataSource;

    private NewsRepository(NewsRemoteDataSource newsRemoteDataSource) {

        mNewsRemoteDataSource = newsRemoteDataSource;
    }

    public static NewsRepository getInstance(NewsRemoteDataSource newsRemoteDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new NewsRepository(newsRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Single<RedditNewsResponse> getTopPosts(String after, String limit) {
        return mNewsRemoteDataSource.getTopPosts(after, limit);
    }
}
