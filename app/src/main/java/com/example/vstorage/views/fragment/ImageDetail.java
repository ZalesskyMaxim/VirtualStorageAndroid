package com.example.vstorage.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.vstorage.R;
import com.example.vstorage.viewmodels.fragmentviewmodel.ImageDetailViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class ImageDetail extends Fragment {
    private ImageDetailViewModel imageDetailViewModel;
    private ImageView imageView;
    private FloatingActionButton addButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment, null);
        imageView = view.findViewById(R.id.image_fr);
        addButton = (FloatingActionButton) Objects.requireNonNull(getActivity()).findViewById(R.id.fab);
        addButton.hide();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addButton.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageDetailViewModel = new ViewModelProvider(getActivity()).get(ImageDetailViewModel.class);
        imageDetailViewModel.getImageUrlMutableLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String url) {
                Glide
                        .with(ImageDetail.this)
                        .load(url)
                        .centerCrop()
                        .into(imageView);
            }
        });
    }
}
