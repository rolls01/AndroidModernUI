package com.odysseydesigns.photosintegrationcourse.models;

import com.odysseyDesigns.googlePhotos.model.AlbumEntry;
import com.odysseyDesigns.googlePhotos.model.PhotoEntry;

public class GooglePhotosItem {
    public GooglePhotosItem(AlbumEntry albumEntry) {
        viewType = ItemType.ALBUM;
        this.albumEntry = albumEntry;
    }

    public GooglePhotosItem(PhotoEntry photoEntry) {
        viewType = ItemType.PHOTO;
        this.photoEntry = photoEntry;
    }

    public GooglePhotosItem(String timestamp) {
        viewType = ItemType.PHOTO_HEADER;
        this.timestamp = timestamp;
    }

    private AlbumEntry albumEntry;
    private PhotoEntry photoEntry;
    private String timestamp;
    private ItemType viewType;
    private boolean isSelected;

    public AlbumEntry getAlbumEntry() {
        return albumEntry;
    }

    public void setAlbumEntry(AlbumEntry albumEntry) {
        this.albumEntry = albumEntry;
    }

    public PhotoEntry getPhotoEntry() {
        return photoEntry;
    }

    public void setPhotoEntry(PhotoEntry photoEntry) {
        this.photoEntry = photoEntry;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getViewType() {
        return viewType.getViewType();
    }

    public void setViewType(ItemType viewType) {
        this.viewType = viewType;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public enum ItemType {
        ALBUM_HRADER("ALBUM_HEADER", 1),
        PHOTO_HEADER("PHOTO_HEADER", 2),
        ALBUM("ALBUM_ITEM", 3),
        PHOTO("PHOTO", 4);


        private String name;
        private int type;

        ItemType(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public int getViewType() {
            return type;
        }
        public String getName() {
            return name;
        }

        public static ItemType fromViewType(int type){
            for(ItemType itemType: ItemType.values()){
                if(itemType.getViewType() == type)
                    return itemType;
            }
            throw new IllegalStateException("Cannot find type!");
        }
    }
}
