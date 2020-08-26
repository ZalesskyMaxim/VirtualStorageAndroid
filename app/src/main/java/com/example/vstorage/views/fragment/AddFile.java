package com.example.vstorage.views.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vstorage.R;
import com.example.vstorage.Service.NavigationService;
import com.example.vstorage.dto.StorageFile;
import com.example.vstorage.listener.Action;
import com.example.vstorage.network.response.ImageResponse;
import com.example.vstorage.viewmodels.fragmentviewmodel.AddFileViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;
import java.util.UUID;

import static com.example.vstorage.network.ApiService.getApiServiceInstance;

public class AddFile extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private ImageView imageView;
    private Button addImageButton;
    private Button addFileButton;
    private EditText nameEditor;
    private EditText dataEditor;
    private Spinner spinner;
    private FloatingActionButton addButton;
    private AddFileViewModel addFileViewModel;
    private String picturePath;
    private String pictureUrl;
    private static final String[] paths = {"None","Folder", "File", "Image"};
    private static final int REQUEST_CODE = 236;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return InitViews(inflater);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addFileViewModel = new ViewModelProvider(getActivity()).get(AddFileViewModel.class);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                imageView.setImageBitmap(getPicture(data.getData()));

                getApiServiceInstance().uploadImage(nameEditor.getText().toString(), picturePath, new Action<ImageResponse>() {
                    @Override
                    public void onSuccess(ImageResponse model) {
                        pictureUrl = model.getImageUrl();
                        addFileButton.setEnabled(true);
                    }
                });
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addButton.show();
    }

    private View InitViews(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.add_file, null);
        imageView = view.findViewById(R.id.image_view);
        addImageButton = view.findViewById(R.id.open_image);
        addImageButton.setOnClickListener(this);
        addFileButton = view.findViewById(R.id.add_btn);
        addFileButton.setOnClickListener(this);
        nameEditor = view.findViewById(R.id.name);
        dataEditor = view.findViewById(R.id.data);

        spinner = (Spinner) view.findViewById(R.id.file_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        addButton = (FloatingActionButton) Objects.requireNonNull(getActivity()).findViewById(R.id.fab);
        addButton.hide();

        return view;
    }

    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    public void onClick(View v) {
        if (v == addFileButton) {
            addFileViewModel.postFile(saveData());
            NavigationService.popFragment(getActivity().getSupportFragmentManager(), this);
        } else {
            checkPermissions();
            addFileButton.setEnabled(false);
        }
    }

    private final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        } else {
            openImageDialog();
        }
    }

    private void openImageDialog() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE);
    }

    private StorageFile saveData() {
        int position = spinner.getSelectedItemPosition();
        switch (position) {
            case 1:
                StorageFile folder = new StorageFile();
                String folderObjectId = UUID.randomUUID().toString();
                folder.setName(nameEditor.getText().toString());
                folder.setParentObjectId(addFileViewModel.getFolderId());
                folder.setType(position);
                folder.setObjectId(folderObjectId);
                return folder;
            case 2:
                StorageFile file = new StorageFile();
                String fileObjectId = UUID.randomUUID().toString();
                file.setName(nameEditor.getText().toString());
                file.setData(dataEditor.getText().toString());
                file.setParentObjectId(addFileViewModel.getFolderId());
                file.setType(position);
                file.setObjectId(fileObjectId);
                return file;
            case 3:
                StorageFile image = new StorageFile();
                String imageObjectId = UUID.randomUUID().toString();
                image.setName(nameEditor.getText().toString());
                image.setData(pictureUrl);
                image.setParentObjectId(addFileViewModel.getFolderId());
                image.setType(position);
                image.setObjectId(imageObjectId);
                return image;
            default: return new StorageFile("Default", "Spinner position" + position);
        }
    }

    private Bitmap getPicture(Uri selectedImage) {
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        picturePath = cursor.getString(columnIndex);
        cursor.close();
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeFile(picturePath, bmOptions);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                dataEditor.setVisibility(View.GONE);
                nameEditor.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                addImageButton.setVisibility(View.GONE);
                addFileButton.setEnabled(false);
                break;
            case 1:
                dataEditor.setVisibility(View.GONE);
                nameEditor.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                addImageButton.setVisibility(View.GONE);
                addFileButton.setEnabled(true);
                break;
            case 2:
                dataEditor.setVisibility(View.VISIBLE);
                nameEditor.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                addImageButton.setVisibility(View.GONE);
                addFileButton.setEnabled(true);
                break;
            case 3:
                dataEditor.setVisibility(View.GONE);
                nameEditor.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                addImageButton.setVisibility(View.VISIBLE);
                addFileButton.setEnabled(true);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
