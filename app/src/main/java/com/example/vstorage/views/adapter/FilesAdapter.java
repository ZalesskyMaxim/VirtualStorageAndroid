package com.example.vstorage.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vstorage.R;
import com.example.vstorage.dto.StorageFile;
import com.example.vstorage.views.holder.FilesHolder;

import java.util.List;

public class FilesAdapter extends RecyclerView.Adapter<FilesHolder> {

    private List<StorageFile> filesList;
    private OnStorageClickListener listener;

    public FilesAdapter(OnStorageClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FilesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.files_item_view, parent, false);
        return new FilesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilesHolder holder, int position) {
        final StorageFile file = filesList.get(position);
        holder.onBindData(file);
        holder.setClickListener(new FilesHolder.OnViewClickListener() {
            @Override
            public void onClick() {
                listener.onClick(file);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filesList != null ? filesList.size() : 0;
    }

    public void setFiles(List<StorageFile> filesList) {
        this.filesList = filesList;
        notifyDataSetChanged();
    }

    public interface OnStorageClickListener {
        void onClick(StorageFile storageFile);
    }

}
