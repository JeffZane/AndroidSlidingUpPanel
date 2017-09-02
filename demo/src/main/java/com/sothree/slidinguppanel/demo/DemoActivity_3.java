package com.sothree.slidinguppanel.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

public class DemoActivity_3 extends BaseActivity {
    private static final String TAG = "yzg---";

    private SlidingUpPanelLayout mLayout;
    private ScrollView mScrollView;
    private PanelState mState;
    private boolean mPause;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.d(TAG, "act onWindowFocusChanged: " + hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "act onCreate: ");
        setContentView(R.layout.activity_demo_3);

        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        findViewById(R.id.main).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changePanelState();
            }
        });
    }

    private void changePanelState() {
        if (mLayout.getPanelState() != PanelState.HIDDEN) {
            mState = mLayout.getPanelState();
            mLayout.setPanelState(PanelState.HIDDEN);
            Log.d(TAG, "onDestroy: isFinishing: " + isFinishing());
        } else if (mLayout.getPanelState() == PanelState.HIDDEN) {
            mLayout.setPanelState(mState);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo, menu);
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (mLayout != null) {
            if (mLayout.getPanelState() == PanelState.HIDDEN) {
                item.setTitle(R.string.action_show);
            } else {
                item.setTitle(R.string.action_hide);
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_toggle: {
                if (mLayout != null) {
                    if (mLayout.getPanelState() != PanelState.HIDDEN) {
                        mLayout.setPanelState(PanelState.HIDDEN);
                        item.setTitle(R.string.action_show);
                    } else {
                        mLayout.setPanelState(PanelState.COLLAPSED);
                        item.setTitle(R.string.action_hide);
                    }
                }
                return true;
            }
            case R.id.action_anchor: {
                if (mLayout != null) {
                    if (mLayout.getAnchorPoint() == 1.0f) {
                        mLayout.setAnchorPoint(0.7f);
                        mLayout.setPanelState(PanelState.ANCHORED);
                        item.setTitle(R.string.action_anchor_disable);
                    } else {
                        mLayout.setAnchorPoint(1.0f);
                        mLayout.setPanelState(PanelState.COLLAPSED);
                        item.setTitle(R.string.action_anchor_enable);
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == PanelState.EXPANDED || mLayout.getPanelState() == PanelState.ANCHORED)) {
            mLayout.setPanelState(PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
