package com.odysseydesigns.photosintegrationcourse.models;

import com.odysseyDesigns.googlePhotos.model.AlbumEntry;
import com.odysseyDesigns.googlePhotos.model.PhotoEntry;


public class GooglePhotosItem {
    private AlbumEntry albumItem;
    private PhotoEntry photoItem;
    private String timestamp;
    private ItemType viewType;
    private boolean isSelected;

    public enum ItemType {
        ALBUM_HEADER("ALBUM_HEADER", 1),
        PHOTO_HEADER("PHOTO_HEADER", 2),
        ALBUM("ALBUM_ITEM", 3),
        PHOTO("PHOTO", 4);

        private String name;
        private int type;
        ItemType(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public int getViewType() { return  type; }

        public String getName() { return name; }

        public static ItemType fromViewType(int type) {
            for(ItemType itemType : ItemType.values()) {
                if(itemType.getViewType() == type) {
                    return itemType;
                }
            }
            throw new IllegalStateException("Cannot find type!" + type);
        }
    }

    public GooglePhotosItem(String timestamp) {
        viewType = ItemType.PHOTO_HEADER;
        this.timestamp = timestamp;
    }

    public GooglePhotosItem(PhotoEntry photoItem) {
        viewType = ItemType.PHOTO;
        this.photoItem = photoItem;
    }

    public GooglePhotosItem(AlbumEntry albumItem) {
        viewType = ItemType.ALBUM;
        this.albumItem = albumItem;
    }

    public AlbumEntry getAlbumEntry() {
        return albumItem;
    }

    public void setAlbumItem(AlbumEntry albumItem) {
        this.albumItem = albumItem;
    }

    public PhotoEntry getPhotoEntry() {
        return photoItem;
    }

    public void setPhotoItem(PhotoEntry photoItem) {
        this.photoItem = photoItem;
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
}
