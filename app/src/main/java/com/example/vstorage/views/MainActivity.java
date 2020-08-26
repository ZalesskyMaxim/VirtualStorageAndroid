package com.example.vstorage.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vstorage.R;
import com.example.vstorage.Service.NavigationService;
import com.example.vstorage.dto.StorageFile;
import com.example.vstorage.listener.Action;
import com.example.vstorage.viewmodels.MainActivityViewModel;
import com.example.vstorage.viewmodels.fragmentviewmodel.AddFileViewModel;
import com.example.vstorage.viewmodels.fragmentviewmodel.ImageDetailViewModel;
import com.example.vstorage.viewmodels.fragmentviewmodel.TextDetailViewModel;
import com.example.vstorage.views.adapter.FilesAdapter;
import com.example.vstorage.views.fragment.AddFile;
import com.example.vstorage.views.fragment.ImageDetail;
import com.example.vstorage.views.fragment.TextDetail;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainActivityViewModel viewModel;
    private ImageDetailViewModel imageDetailViewModel;
    private TextDetailViewModel textDetailViewModel;
    private AddFileViewModel addFileViewModel;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private FloatingActionButton addFab;
    private ImageDetail imageDetailFragment;
    private TextDetail textDetailFragment;
    private AddFile addFileFragment;

    private String folderObjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageDetailViewModel = new ViewModelProvider(this).get(ImageDetailViewModel.class);
        textDetailViewModel = new ViewModelProvider(this).get(TextDetailViewModel.class);
        addFileViewModel = new ViewModelProvider(this).get(AddFileViewModel.class);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        if (!viewModel.isLogged()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {

            initViews();
            setupToolbar();

            final FilesAdapter adapter = new FilesAdapter(recyclerClickListener);
            recyclerView.setAdapter(adapter);

            viewModel.getAllFiles().observe(this, new Observer<List<StorageFile>>() {
                @Override
                public void onChanged(List<StorageFile> storageFiles) {
                    if (storageFiles.size() > 0) {
                        folderObjectId = storageFiles.get(0).getParentObjectId();
                    }
                    adapter.setFiles(storageFiles);
                }
            });

            setClickListener();
        }
    }

    private FilesAdapter.OnStorageClickListener recyclerClickListener = new FilesAdapter.OnStorageClickListener() {
        @Override
        public void onClick(StorageFile storageFile) {
            switch (storageFile.getType()) {
                case 1:

                    break;
                case 2:
                    viewModel.getText(storageFile.getObjectId()).observe(MainActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            textDetailViewModel.postValue(s);
                        }
                    });
                    NavigationService.pushFragment(getSupportFragmentManager(), R.id.fragment_container, textDetailFragment);
                    break;
                case 3:
                    viewModel.getUrl(storageFile.getObjectId()).observe(MainActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            imageDetailViewModel.postValue(s);
                        }
                    });
                    NavigationService.pushFragment(getSupportFragmentManager(), R.id.fragment_container, imageDetailFragment);
                    break;
                default:
                    Log.e("MainActivity", "UnknownType " + storageFile.getType());
                    break;
            }
        }
    };

    private void initViews() {


        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        imageDetailFragment = new ImageDetail();
        textDetailFragment = new TextDetail();
        addFileFragment = new AddFile();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        addFab = findViewById(R.id.fab);
    }

    private void setClickListener() {
        addFab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == addFab) {
            addFileViewModel.setFolderId(folderObjectId);
            addFileViewModel.getFileMutableLiveData().observe(this, new Observer<StorageFile>() {
                @Override
                public void onChanged(StorageFile storageFile) {
                    viewModel.insertStorageFile(storageFile);
                }
            });
            NavigationService.pushFragment(getSupportFragmentManager(), R.id.fragment_container, addFileFragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.sign_out: {//TODO: move into method
                viewModel.logout(new Action<Boolean>() {
                    @Override
                    public void onSuccess(Boolean success) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(String error) {
                    }
                });
                return true;
            }
            case R.id.load_data: {
                viewModel.downloadAllData();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
