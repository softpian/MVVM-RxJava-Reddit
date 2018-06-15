package com.softpian.rxmvvmreddit.viewmodel;

import com.softpian.rxmvvmreddit.data.source.NewsRepository;
import com.softpian.rxmvvmreddit.data.source.remote.NewsRemoteDataSource;

public class NewsModule {

    public static NewsViewModel createNewsViewModel() {

        return new NewsViewModel(NewsRepository.getInstance(NewsRemoteDataSource.getInstance()));
    }
}
