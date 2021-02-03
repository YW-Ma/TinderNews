package com.study.tindernews.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.study.tindernews.ui.home.HomeViewModel;
import com.study.tindernews.ui.search.SearchViewModel;

// 通过工厂模式来创建ViewModel
// 这里就是Factory，输入对应的class，就可以获得HomeViewMode或者SearchViewModel
public class NewsViewModelFactory implements ViewModelProvider.Factory {

    private final NewsRepository repository;

    public NewsViewModelFactory(NewsRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(repository);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(repository);
        } else {
            throw new IllegalStateException("Unknown ViewModel");
        }
    }
}