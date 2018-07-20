package com.odysseydesigns.photosintegrationcourse.ui.googlePhotos.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class GridRecycleView extends RecyclerView {
    private int columnWidth = -1;
    private GridLayoutManager gridLayoutManager;

    public GridRecycleView(Context context) {
        super(context);
    }

    public GridRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GridRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if(columnWidth > 0){
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            gridLayoutManager.setSpanCount(spanCount);
        }
    }

    private void initView(Context context, AttributeSet attributeSet) {
        int columnCount = getColumnCount(context, attributeSet);

        gridLayoutManager = new GridLayoutManager(getContext(), columnCount);
        setHasFixedSize(true);
        setItemAnimator(new DefaultItemAnimator());
//        addItemDecoration();
    }

    private int getColumnCount(Context context, AttributeSet attributeSet) {
        int columnCount;
        int screenWidth = getResources().getDisplayMetrics().widthPixels - 20;

        int[] attrsArray = {
                android.R.attr.columnWidth
        };

        TypedArray array = context.obtainStyledAttributes(attributeSet, attrsArray);
        columnWidth = array.getDimensionPixelSize(0, -1);

        array.recycle();
        columnCount = Math.round((float) screenWidth / (float) columnWidth);

        return columnCount;
    }
}

