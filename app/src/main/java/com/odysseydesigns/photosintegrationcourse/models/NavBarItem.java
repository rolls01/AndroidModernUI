package com.odysseydesigns.photosintegrationcourse.models;

import com.odysseydesigns.photosintegrationcourse.R;

public enum NavBarItem {
    GOOGLE_PHOTOS(R.id.google_photos),
    TWITTER(R.id.twitter),
    FACEBOOK(R.id.facebook),
    SETTINGS(R.id.user_settings);


    private int itemId;
    NavBarItem(int item_id) {
        this.itemId = item_id;
    }

    public int getItemId() {
        return itemId;
    }

    public static NavBarItem fromViewId(int viewId){
        for(NavBarItem navBarItem: NavBarItem.values()){
            if(navBarItem.getItemId() == viewId)
                return navBarItem;
        }
        new IllegalStateException("Cannot find viewId");
        return null;
    }
}
