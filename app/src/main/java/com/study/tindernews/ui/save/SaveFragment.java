package com.study.tindernews.ui.save;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.tindernews.R;
import com.study.tindernews.databinding.FragmentSaveBinding;
import com.study.tindernews.repository.NewsRepository;
import com.study.tindernews.repository.NewsViewModelFactory;

public class SaveFragment extends Fragment {
    // layout binding and data binding

    private FragmentSaveBinding binding;
    private SaveViewModel viewModel;

    public SaveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { // Bundle (key->value) 但是不是HashMap，负责process之间传数据。
        // Inflate the layout for this fragment -- 这个inflater的context应该是activity了。
        // return inflater.inflate(R.layout.fragment_save, container, false);
        binding =  FragmentSaveBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NewsRepository repository = new NewsRepository(getContext());
        SavedNewsAdapter savedNewsAdapter = new SavedNewsAdapter();
        binding.newsSavedRecyclerView.setAdapter(savedNewsAdapter);
        binding.newsSavedRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        // Why new LinearLayout → row by row recycler view’s manager


        // Creates ViewModelProvider, which will create ViewModels via the given Factory
        // and retain them in a store of the given ViewModelStoreOwner.
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, new NewsViewModelFactory(repository));
        viewModel = viewModelProvider.get(SaveViewModel.class); // Returns an existing ViewModel or creates a new one in the scope (usually, a fragment or an activity)
        viewModel
                .getAllSavedArticles()
                .observe(
                        getViewLifecycleOwner(),
                        savedArticles -> {
                            if (savedArticles != null) {
                                Log.d("SaveFragment", savedArticles.toString());
                                savedNewsAdapter.setArticles(savedArticles); // data binding
                            }
                        });
    }
}