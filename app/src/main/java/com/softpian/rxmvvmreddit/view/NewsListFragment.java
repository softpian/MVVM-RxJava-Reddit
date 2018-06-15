package com.softpian.rxmvvmreddit.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.softpian.rxmvvmreddit.R;
import com.softpian.rxmvvmreddit.viewmodel.NewsModule;
import com.softpian.rxmvvmreddit.viewmodel.NewsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewsListFragment extends Fragment {

    private NewsViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable;

    @BindView(R.id.rvNewsList) RecyclerView mNewsListView;
    @BindView(R.id.tvError) TextView mErrorView;
    @BindView(R.id.pbLoading) ProgressBar mLoadingView;

    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mNewsListView.setAdapter(new NewsListAdapter());
        mNewsListView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel = NewsModule.createNewsViewModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        bindViewModel();
    }

    @Override
    public void onPause() {
        unbindViewModel();
        super.onPause();
    }

    private void bindViewModel() {
        mCompositeDisposable = new CompositeDisposable();

        mCompositeDisposable.add(mViewModel.getNewsModel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mViewModel.newsUpdated(), mViewModel.onError())
        );

        mCompositeDisposable.add(mViewModel.getLoadingIndicator()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isLoading -> {
                    mLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                    mNewsListView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
                    mErrorView.setVisibility(isLoading ? View.GONE : mErrorView.getVisibility());
                })
        );

        mCompositeDisposable.add(mViewModel.getNewsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(((NewsListAdapter)mNewsListView.getAdapter())::setRedditNewsResponse)
        );

        mCompositeDisposable.add(mViewModel.getError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(error -> {
                    if (error == -1) {
                        mErrorView.setVisibility(View.GONE);
                        mErrorView.setText(null);
                    } else {
                        mErrorView.setVisibility(View.VISIBLE);
                        mErrorView.setText(error);
                        mNewsListView.setVisibility(View.GONE);
                    }
                })
        );
    }

    private void unbindViewModel() {
        mCompositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }
}
