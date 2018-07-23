package com.odysseydesigns.photosintegrationcourse.ui.googlePhotos;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.odysseyDesigns.googlePhotos.model.AlbumEntry;
import com.odysseyDesigns.googlePhotos.model.PhotoEntry;
import com.odysseydesigns.photosintegrationcourse.models.GooglePhotosItem;
import com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.viewHolders.AlbumViewHolder;
import com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.viewHolders.BaseViewHolder;
import com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.viewHolders.GooglePhotosViewHolder;
import com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.viewHolders.HeaderViewHolder;

import java.util.List;

public class AlbumGridAdapter extends BaseGridAdapter {

    public AlbumGridAdapter(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    private AppCompatActivity appCompatActivity;


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GooglePhotosItem.ItemType type = GooglePhotosItem.ItemType.fromViewType(viewType);
        if (type == null)
            throw new NullPointerException("No ViewHolder fro this type!");

        switch (type) {
            case ALBUM:
                return new AlbumViewHolder(appCompatActivity, parent);
            case PHOTO:
                return new GooglePhotosViewHolder(appCompatActivity, this, parent);
            case ALBUM_HRADER:
            case PHOTO_HEADER:
                return new HeaderViewHolder(appCompatActivity, parent);
            default:
                throw new IllegalStateException("Unknown view type: " + viewType);
        }
    }

    public void setAlbumList(List<AlbumEntry> albumEntries){
        googlePhotosManager.setAlbumList(albumEntries);
        notifyDataSetChanged();
    }

    public void setPhotosList(List<PhotoEntry> photoEntries){
        googlePhotosManager.setPhotosList(photoEntries);
        notifyDataSetChanged();
    }

}
