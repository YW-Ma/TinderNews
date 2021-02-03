package com.study.tindernews.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.study.tindernews.model.NewsResponse;
import com.study.tindernews.network.NewsApi;
import com.study.tindernews.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private final NewsApi newsApi;
    public NewsRepository(Context context) {
        newsApi = RetrofitClient.newInstance(context).create(NewsApi.class);
    }

    //  理解执行顺序：
    //  1. task enqueue
    //  2. return 这个LiveData（空的）
    //  3. 等消息回来了，执行callback里面的setValue（给里面填数据）
    //  4. View subscribe了LiveData，那么它会知道里面有数据了。

    // Part 1 implement getTopHeadlines API in NewsRepository;
    public LiveData<NewsResponse> getTopHeadlines(String country) {
        MutableLiveData<NewsResponse> topHeadlinesLiveData = new MutableLiveData<>();
        Call<NewsResponse> task = newsApi.getTopHeadlines(country);
        task.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    topHeadlinesLiveData.setValue(response.body());
                } else {
                    topHeadlinesLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                topHeadlinesLiveData.setValue(null);
            }
        });
        return topHeadlinesLiveData; // the LiveData container.
    }

    // Part 2 implement searchNews API in NewsRepository:
    public LiveData<NewsResponse> searchNews(String query) {
        MutableLiveData<NewsResponse> everyThingLiveData = new MutableLiveData<>();
        newsApi.getEverything(query, 40)
                .enqueue(
                        new Callback<NewsResponse>() {
                            @Override
                            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                                if (response.isSuccessful()) {
                                    everyThingLiveData.setValue(response.body());
                                } else {
                                    everyThingLiveData.setValue(null);
                                }
                            }

                            @Override
                            public void onFailure(Call<NewsResponse> call, Throwable t) {
                                everyThingLiveData.setValue(null);
                            }
                        });
        return everyThingLiveData;
    }
}
