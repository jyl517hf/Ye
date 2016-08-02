package com.eaotin.framework.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.eaotin.framework.event.EventId;
import com.eaotin.framework.event.EventListener;
import com.eaotin.framework.event.UIEventListener;

/**
 * BaseActivity.java是...。
 *
 * @author 金玉龙
 * @version 2.0 16/8/2
 * @modify
 */
public class BaseActivity extends Activity {
    protected NotificationManager notificationManager;
    /**
     * activity是否正在销毁
     * 主要用以判断逻辑层回调后是否继续执行
     */
    private boolean mCreated = false;

    public boolean isCreated() {
        return mCreated;
    }

    // event
    private UIEventListener.Helper mEventListenerHelper = new UIEventListener.Helper();

    public UIEventListener createUIEventListener(EventListener listener) {
        return mEventListenerHelper.createUIEventListener(listener);
    }

    public void addUIEventListener(EventId id, EventListener listener) {
        mEventListenerHelper.add(id, listener);
    }

    public void removeUIEventListener(EventListener listener) {
        mEventListenerHelper.remove(listener);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mCreated = true;
        mEventListenerHelper.setHost(this);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    protected void onDestroy() {
        mCreated = false;
        mEventListenerHelper.clear();
        super.onDestroy();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


}
