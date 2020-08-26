package com.example.vstorage.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vstorage.R;
import com.example.vstorage.viewmodels.fragmentviewmodel.TextDetailViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class TextDetail extends Fragment {
    private TextDetailViewModel textDetailViewModel;
    private TextView textView;
    private FloatingActionButton addButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_fragment, null);
        textView = view.findViewById(R.id.text_detail);
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
        textDetailViewModel = new ViewModelProvider(getActivity()).get(TextDetailViewModel.class);
        textDetailViewModel.getTextMutableLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String text) {
                textView.setText(text);
            }
        });
    }
}