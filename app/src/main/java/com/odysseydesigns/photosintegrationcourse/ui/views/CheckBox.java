package com.odysseydesigns.photosintegrationcourse.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.PictureDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.odysseydesigns.photosintegrationcourse.R;

public class CheckBox extends AppCompatImageView {
    private static final String TAG = CheckBox.class.getSimpleName();
    private PictureDrawable checked;
    private PictureDrawable unchecked;
    private boolean isChecked;

    public CheckBox(Context context) {
        super(context);
        initView(context, null);
    }

    public CheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState state = new SavedState(superState);
        state.isChecked = this.isChecked;

        return state;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if(!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        setChecked(savedState.isChecked);
    }

    private void initView(Context context, AttributeSet attrs) {
        String packageName = context.getPackageName();
        setSaveEnabled(true);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        if (attrs != null) {
            getCheckBoxVectors(packageName, context, attrs);
        } else {
            getCheckBoxVectors(packageName, "check_circle_black", "check_circle_off");
        }
        if(unchecked != null && checked != null) {
            setImageDrawable(unchecked);
        } else {
            Log.e(TAG, "Checkbox vectors not found!");
        }
    }

    private void getCheckBoxVectors(String packageName, Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CheckBox,
                0, 0);

        String svgFilenameOn = a.getString(R.styleable.CheckBox_on);
        String svgFilenameOff = a.getString(R.styleable.CheckBox_off);

        if (!TextUtils.isEmpty(svgFilenameOn) && !TextUtils.isEmpty(svgFilenameOff)) {
            getCheckBoxVectors(packageName, svgFilenameOn, svgFilenameOff);
        } else {
            getCheckBoxVectors(packageName, "check_circle_black", "check_circle_off");
        }
    }

    private void getCheckBoxVectors(String packageName, String filenameOn, String filenameOff) {
        int checkedRes = getResources().getIdentifier(filenameOn, "raw", packageName);
        int uncheckedRes = getResources().getIdentifier(filenameOff, "raw", packageName);

        checked = loadVectorFromSvgFile(checkedRes);
        unchecked = loadVectorFromSvgFile(uncheckedRes);
    }

    private PictureDrawable loadVectorFromSvgFile(int rawFileId) {
        if (rawFileId > 0) {
            try {
                SVG svg = SVG.getFromResource(getContext(), rawFileId);
                return new PictureDrawable(svg.renderToPicture());
            } catch (SVGParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public void onClick() {
        if (isChecked) {
            isChecked = false;
            setImageDrawable(unchecked);
        } else {
            isChecked = true;
            setImageDrawable(checked);
        }
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        String content;

        if(isChecked) {
            content = "Image Selected";
            setImageDrawable(checked);
        } else {
            content = "Image unselected";
            setImageDrawable(unchecked);
        }
        setContentDescription(content);
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);
    }

    public boolean isChecked() {
        return isChecked;
    }

    private static class SavedState extends BaseSavedState {
        boolean isChecked;
        boolean states[];

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel in) {
            super(in);
            in.readBooleanArray(states);
            if (states.length == 1) {
                this.isChecked = states[0];
            }
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeBooleanArray(this.states);
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    @Override
                    public SavedState createFromParcel(Parcel source) {
                        return new SavedState(source);
                    }

                    @Override
                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

}
