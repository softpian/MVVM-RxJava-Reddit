package com.softpian.rxmvvmreddit.viewmodel;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.softpian.rxmvvmreddit.R;
import com.softpian.rxmvvmreddit.data.model.RedditNewsResponse;
import com.softpian.rxmvvmreddit.data.source.NewsRepository;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;


public class NewsViewModel {

    private final NewsRepository mNewsRepository;

    private final BehaviorRelay<RedditNewsResponse> mNewsListRelay;

    private final BehaviorRelay<Boolean> mLoadingIndicatorRelay;

    private final BehaviorRelay<Integer> mErrorRelay;

    NewsViewModel(NewsRepository newsRepository) {

        mNewsRepository = newsRepository;

        mNewsListRelay = BehaviorRelay.create();
        mLoadingIndicatorRelay = BehaviorRelay.create();
        mErrorRelay = BehaviorRelay.create();

    }

    public Single<RedditNewsResponse> getNewsModel() {

        return mNewsRepository.getTopPosts("", "20")
                    .doOnSubscribe(__ -> mLoadingIndicatorRelay.accept(true))
                    .doOnEvent((d, t) -> mLoadingIndicatorRelay.accept(false));
    }

    public Observable<RedditNewsResponse> getNewsList() {
        return mNewsListRelay;
    }

    public Observable<Boolean> getLoadingIndicator() {
        return mLoadingIndicatorRelay;
    }

    public Observable<Integer> getError() {
        return mErrorRelay;
    }

    public Consumer<RedditNewsResponse> newsUpdated() {
        mErrorRelay.accept(-1);
        return mNewsListRelay;
    }

    public Consumer<Throwable> onError() {
        return throwable -> {
            mErrorRelay.accept(R.string.error_loading_failed);
        };
    }
}
