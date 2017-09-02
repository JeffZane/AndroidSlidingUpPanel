package com.sothree.slidinguppanel.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class BaseActivity extends ActionBarActivity {

    private boolean mHasFocus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        mHasFocus = hasFocus;
        Log.d("yzg---", "base onWindowFocusChanged: " + hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }

    public boolean hasFocus() {
        return mHasFocus;
    }
}
