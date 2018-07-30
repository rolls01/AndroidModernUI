package com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.viewHolders;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
//import com.odysseyDesigns.googlePhotos.R;
import com.odysseyDesigns.googlePhotos.model.AlbumEntry;
import com.odysseydesigns.photosintegrationcourse.R;
import com.odysseydesigns.photosintegrationcourse.models.GooglePhotosItem;
import com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.BaseGridAdapter;
import com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.GooglePhotosFragment;
import com.squareup.picasso.Picasso;


public class AlbumViewHolder extends BaseViewHolder {
    private ImageView imageView;
    private AppCompatActivity appCompatActivity;

    public AlbumViewHolder(AppCompatActivity appCompatActivity, ViewGroup parent) {
        super(LayoutInflater.from(appCompatActivity).inflate(R.layout.google_photos_item, parent, false));
        imageView = (ImageView) getRootView().findViewById(R.id.thumb);
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public void setData(BaseGridAdapter adapter, GooglePhotosItem googlePhotosItem, int position) {
        final AlbumEntry albumEntry = googlePhotosItem.getAlbumEntry();
        String thumb = albumEntry.getMediaGroup().getContents().get(0).getUrl();
        Picasso.get().load(thumb).into(imageView);
        getRootView().setOnClickListener(new ItemClickHandler(adapter));
    }

    private class ItemClickHandler implements View.OnClickListener{
        private final BaseGridAdapter baseGridAdapter;
        public ItemClickHandler(BaseGridAdapter baseGridAdapter) {
            super();
            this.baseGridAdapter = baseGridAdapter;
        }

        @Override
        public void onClick(View view) {
            if(getAdapterPosition() != RecyclerView.NO_POSITION){
                long albumId = baseGridAdapter.getItem(getAdapterPosition()).getAlbumEntry().getPhotoId();
                FragmentManager supportFragmentManager = appCompatActivity.getSupportFragmentManager();
                GooglePhotosFragment googlePhotosFragment = (GooglePhotosFragment) supportFragmentManager.findFragmentByTag(GooglePhotosFragment.class.getSimpleName());
                googlePhotosFragment.loadPhotos(albumId);
            }
        }
    }

}
