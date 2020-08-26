package com.example.vstorage.db.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.vstorage.db.VStorageDB;
import com.example.vstorage.db.dao.FileDao;
import com.example.vstorage.db.dao.FolderDao;
import com.example.vstorage.db.dao.ImageDao;
import com.example.vstorage.db.dao.StorageFileDao;
import com.example.vstorage.db.entity.FileEntity;
import com.example.vstorage.db.entity.FolderEntity;
import com.example.vstorage.db.entity.ImageEntity;
import com.example.vstorage.db.mapper.FileMapper;
import com.example.vstorage.db.mapper.FolderMapper;
import com.example.vstorage.db.mapper.ImageMapper;
import com.example.vstorage.dto.StorageFile;
import com.example.vstorage.network.dto.File;
import com.example.vstorage.network.dto.Folder;
import com.example.vstorage.network.dto.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StorageRepository {
    private FileDao fileDao;
    private FolderDao folderDao;
    private ImageDao imageDao;
    private StorageFileDao storageFileDao;
    private LiveData<List<StorageFile>> getAllRootFiles;
    private LiveData<String> imageUrl;

    public StorageRepository(Application application) {
        VStorageDB db = VStorageDB.getInstance(application);
        fileDao = db.fileDao();
        folderDao = db.folderDao();
        imageDao = db.imageDao();
        storageFileDao = db.storageFileDao();
        getAllRootFiles = storageFileDao.getAllRootFiles();

    }

    public void insertFiles(ArrayList<File> fileList){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new InsertFilesSingleThread(fileDao, FileMapper.toEntityList(fileList)));
    }

    public void insertFolders(ArrayList<Folder> folderList){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new InsertFoldersSingleThread(folderDao, FolderMapper.toEntityList(folderList)));
    }

    public void insertImages(ArrayList<Image> imageList){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new InsertImagesSingleThread(imageDao, ImageMapper.toEntityList(imageList)));
    }

    public void insertFile(StorageFile file){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new InsertFileSingleThread(fileDao, FileMapper.toEntity(file)));
    }

    public void insertFolder(StorageFile folder){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new InsertFolderSingleThread(folderDao, FolderMapper.toEntity(folder)));
    }

    public void insertImage(StorageFile image){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new InsertImageSingleThread(imageDao, ImageMapper.toEntity(image)));
    }

    public LiveData<List<StorageFile>> getGetAllRootFiles() {
        return getAllRootFiles;
    }

    public LiveData<String> getImageUrl(String imageObjectId) {
        return storageFileDao.getImageUrl(imageObjectId);
    }

    public LiveData<String> getFileText(String textObjectId) {
        return storageFileDao.getFileText(textObjectId);
    }

    private static class InsertFilesSingleThread implements Runnable {
        private FileDao fileDao;
        private ArrayList<FileEntity> files;

        private InsertFilesSingleThread(FileDao fileDao, ArrayList<FileEntity> files) {
            this.fileDao = fileDao;
            this.files = files;
        }

        @Override
        public void run() {
            fileDao.insertFiles(files);
        }
    }

    private static class InsertFileSingleThread implements Runnable {
        private FileDao fileDao;
        private FileEntity file;

        private InsertFileSingleThread(FileDao fileDao, FileEntity file) {
            this.fileDao = fileDao;
            this.file = file;
        }

        @Override
        public void run() {
            fileDao.insertFile(file);
        }
    }

    private static class InsertFoldersSingleThread implements Runnable {
        private FolderDao folderDao;
        ArrayList<FolderEntity> folders;

        private InsertFoldersSingleThread(FolderDao folderDao, ArrayList<FolderEntity> folders) {
            this.folderDao = folderDao;
            this.folders = folders;
        }

        @Override
        public void run() {
            folderDao.insertFolders(folders);
        }
    }

    private static class InsertFolderSingleThread implements Runnable {
        private FolderDao folderDao;
        FolderEntity folder;

        private InsertFolderSingleThread(FolderDao folderDao, FolderEntity folder) {
            this.folderDao = folderDao;
            this.folder = folder;
        }

        @Override
        public void run() {
            folderDao.insertFolder(folder);
        }
    }

    private static class InsertImagesSingleThread implements Runnable {
        private ImageDao imageDao;
        ArrayList<ImageEntity> images;

        private InsertImagesSingleThread(ImageDao imageDao, ArrayList<ImageEntity> images) {
            this.imageDao = imageDao;
            this.images = images;
        }

        @Override
        public void run() {
            imageDao.insertImages(images);
        }
    }

    private static class InsertImageSingleThread implements Runnable {
        private ImageDao imageDao;
        ImageEntity image;

        private InsertImageSingleThread(ImageDao imageDao, ImageEntity image) {
            this.imageDao = imageDao;
            this.image = image;
        }

        @Override
        public void run() {
            imageDao.insertImage(image);
        }
    }
}
