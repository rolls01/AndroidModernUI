package com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.viewHolders;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.odysseyDesigns.googlePhotos.model.PhotoEntry;
import com.odysseydesigns.photosintegrationcourse.R;
import com.odysseydesigns.photosintegrationcourse.models.GooglePhotosItem;
import com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.AlbumGridAdapter;
import com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.BaseGridAdapter;
import com.squareup.picasso.Picasso;

public class GooglePhotosViewHolder extends BaseViewHolder implements View.OnClickListener {
    private ImageView imageView;
    private AppCompatActivity appCompatActivity;

    public GooglePhotosViewHolder(AppCompatActivity appCompatActivity, AlbumGridAdapter albumGridAdapter, ViewGroup parent) {
        super(LayoutInflater.from(appCompatActivity).inflate(R.layout.google_photos_item, parent, false));
        imageView = (ImageView) getRootView().findViewById(R.id.thumb);
        getRootView().setOnClickListener(this);
    }

    @Override
    public void setData(BaseGridAdapter adapter, GooglePhotosItem googlePhotosItem, int position) {
        PhotoEntry photoEntry = googlePhotosItem.getPhotoEntry();
        String thumb = photoEntry.getMediaGroup().getContents().get(0).getUrl();
        Picasso.get().load(thumb).into(imageView);
    }

    @Override
    public void onClick(View view) {
        
    }
}
