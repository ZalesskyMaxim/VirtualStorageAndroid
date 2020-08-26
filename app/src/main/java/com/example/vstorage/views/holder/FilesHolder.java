package com.example.vstorage.views.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vstorage.R;
import com.example.vstorage.dto.StorageFile;

public final class FilesHolder extends RecyclerView.ViewHolder {
    private TextView titleTxt;
    private TextView descTxt;
    private OnViewClickListener clickListener;

    public FilesHolder(@NonNull View itemView) {
        super(itemView);

        titleTxt = itemView.findViewById(R.id.title_txt);
        descTxt = itemView.findViewById(R.id.desc_txt);
    }

    public void onBindData(StorageFile file) {
        titleTxt.setText(file.getName());
        descTxt.setText(file.getObjectId());
    }

    public void setClickListener(OnViewClickListener listener) {
        this.clickListener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick();
            }
        });
    }

    public interface OnViewClickListener {
        void onClick();
    }
}
