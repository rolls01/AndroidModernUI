package com.odysseydesigns.photosintegrationcourse.ui.googlePhotos;

import android.util.Log;

import com.odysseyDesigns.googlePhotos.model.AlbumEntry;
import com.odysseyDesigns.googlePhotos.model.PhotoEntry;
import com.odysseydesigns.photosintegrationcourse.models.GooglePhotosItem;

import java.util.ArrayList;
import java.util.List;

public class GooglePhotosManager {

    private static final String TAG = GooglePhotosManager.class.getSimpleName();
    public GooglePhotosManager() {
        this.googlePhotosItemList = new ArrayList<>();
    }

    private List<GooglePhotosItem> googlePhotosItemList;


    public GooglePhotosItem getGooglePhotosItem(int position) {
        return googlePhotosItemList.get(position);
    }

    public void setAlbumList(List<AlbumEntry> albumEntries) {
        this.googlePhotosItemList.clear();
        for(AlbumEntry albumEntry: albumEntries){
            GooglePhotosItem item = new GooglePhotosItem(albumEntry);
            googlePhotosItemList.add(item);
        }
    }

    public int getItemCount() {
        return googlePhotosItemList.size();
    }

    public GooglePhotosItem getItem(int position){
        return googlePhotosItemList.get(position);
    }

    public void setPhotosList(List<PhotoEntry> photoEntries){
        this.googlePhotosItemList.clear();
        String header = "";
        for(PhotoEntry photoEntry: photoEntries){
            Log.w(TAG, "Date: "+ photoEntry.getGphotoTimestamp());
            String timestamp = photoEntry.getUpdated().getBody();
            Log.w(TAG, "timestamp: " + timestamp);
            if(!header.equals(timestamp)){
                googlePhotosItemList.add(new GooglePhotosItem(timestamp));
                header = timestamp;
            }
            GooglePhotosItem item = new GooglePhotosItem(photoEntry);
            googlePhotosItemList.add(item);
        }
    }
}
