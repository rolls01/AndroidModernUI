package com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.viewHolders;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.odysseydesigns.photosintegrationcourse.R;
import com.odysseydesigns.photosintegrationcourse.models.GooglePhotosItem;
import com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.BaseGridAdapter;


public class HeaderViewHolder extends BaseViewHolder {
    private TextView textView;
    private ImageView imageView;
    private AppCompatActivity appCompatActivity;
    public HeaderViewHolder(AppCompatActivity appCompatActivity, ViewGroup parent) {
        super(LayoutInflater.from(appCompatActivity).inflate(R.layout.google_photos_header, parent, false));
        textView = (TextView) getRootView().findViewById(R.id.label);
    }

    @Override
    public void setData(BaseGridAdapter adapter, GooglePhotosItem googlePhotosItem, int position) {
        textView.setText(googlePhotosItem.getTimestamp());
    }
}
