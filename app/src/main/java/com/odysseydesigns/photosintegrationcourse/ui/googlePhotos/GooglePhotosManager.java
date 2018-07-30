package com.odysseydesigns.photosintegrationcourse.ui.googlePhotos;

import com.odysseyDesigns.googlePhotos.model.AlbumEntry;
import com.odysseyDesigns.googlePhotos.model.PhotoEntry;
import com.odysseydesigns.photosintegrationcourse.models.GooglePhotosItem;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
        googlePhotosItemList.clear();
        String header = "";
        photoEntries.sort((o1, o2) -> o1.getGphotoTimestamp() < o2.getGphotoTimestamp() ? 0 : 1);
        for(PhotoEntry entry : photoEntries) {
            Timestamp ts = new Timestamp(entry.getGphotoTimestamp());
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedTime = output.format(ts);

            if(!header.equals(formattedTime)) {
                googlePhotosItemList.add(new GooglePhotosItem(formattedTime));
                header = formattedTime;
            }
            GooglePhotosItem item = new GooglePhotosItem(entry);
            googlePhotosItemList.add(item);
        }
    }
}
