package com.odysseydesigns.photosintegrationcourse.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.odysseydesigns.photosintegrationcourse.models.GooglePhotosItem;

public class GridRecyclerView extends RecyclerView {

    private int columnWidth = -1;
    private GridLayoutManager manager;

    public GridRecyclerView(Context context) {
        super(context);
        initView(context, null);
    }

    public GridRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public GridRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    private int getColumnCount(Context context, AttributeSet attrs) {
        int columnCount;
        int screenWidth = getResources().getDisplayMetrics().widthPixels - 20;

        int[] attrsArray = {
                android.R.attr.columnWidth
        };

        TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
        columnWidth = array.getDimensionPixelSize(0, -1);

        array.recycle();
        columnCount = Math.round((float) screenWidth / (float) columnWidth);

        return columnCount;
    }

    private void initView(Context context, AttributeSet attrs) {
        int columnCount = getColumnCount(context, attrs);

        manager = new GridLayoutManager(getContext(), columnCount);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(getAdapter() == null) return 0;

                int itemViewType = getAdapter().getItemViewType(position);
                if (itemViewType == GooglePhotosItem.ItemType.PHOTO_HEADER.getViewType()) {
                    return manager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });
        setHasFixedSize(true);
        setLayoutManager(manager);
        setItemAnimator(new DefaultItemAnimator());

    }


    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (columnWidth > 0) {
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            manager.setSpanCount(spanCount);
        }
    }
}

