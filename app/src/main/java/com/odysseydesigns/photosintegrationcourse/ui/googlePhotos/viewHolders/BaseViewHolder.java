package com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.odysseydesigns.photosintegrationcourse.models.GooglePhotosItem;
import com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.BaseGridAdapter;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public abstract void setData(BaseGridAdapter adapter, GooglePhotosItem googlePhotosItem, int position);
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected View getRootView(){
        return this.itemView;
    }
}
