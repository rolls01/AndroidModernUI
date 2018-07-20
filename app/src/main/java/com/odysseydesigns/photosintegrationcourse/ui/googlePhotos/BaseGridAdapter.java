package com.odysseydesigns.photosintegrationcourse.ui.googlePhotos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.odysseydesigns.photosintegrationcourse.models.GooglePhotosItem;
import com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.viewHolders.BaseViewHolder;

public abstract class BaseGridAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    protected GooglePhotosManager googlePhotosManager;

    public BaseGridAdapter() {
        super();
        googlePhotosManager = new GooglePhotosManager();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.setData(this, getItem(position), position);
    }

    @Override
    public int getItemCount() {
        return googlePhotosManager.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        GooglePhotosItem item = getItem(position);
        return item.getViewType();
    }

    public GooglePhotosItem getItem(int position) {
        return googlePhotosManager.getItem(position);
    }
}
