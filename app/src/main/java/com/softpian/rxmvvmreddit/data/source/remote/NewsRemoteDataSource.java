package com.softpian.rxmvvmreddit.data.source.remote;

import com.softpian.rxmvvmreddit.data.model.RedditNewsResponse;
import com.softpian.rxmvvmreddit.data.source.NewsDataSource;
import com.softpian.rxmvvmreddit.network.RestfulService;
import com.softpian.rxmvvmreddit.network.RxRedditApi;
import com.softpian.rxmvvmreddit.util.Constant;

import io.reactivex.Single;

public class NewsRemoteDataSource implements NewsDataSource {

    private final RxRedditApi mRxRedditApi = RestfulService.getInstance().createRetrofit(RxRedditApi.class, Constant.BASE_URL);
    private static NewsRemoteDataSource INSTANCE;

    private NewsRemoteDataSource() {}

    public static NewsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewsRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public Single<RedditNewsResponse> getTopPosts(String after, String limit) {
        return mRxRedditApi.getTopPosts(after, limit);
    }
}
