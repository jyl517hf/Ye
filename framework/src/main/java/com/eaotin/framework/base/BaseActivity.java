package com.eaotin.framework.base;

import android.app.Fragment;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.eaotin.framework.event.EventId;
import com.eaotin.framework.event.EventListener;
import com.eaotin.framework.event.UIEventListener;

/**
 * BaseActivity.java是APP的所有Activity的基类
 *
 * @author 金玉龙
 * @version 2.0 16/8/2
 * @modify
 */
public class BaseActivity extends AppCompatActivity {
    protected NotificationManager notificationManager;
    /**
     * activity是否正在销毁
     * 主要用以判断逻辑层回调后是否继续执行
     */
    private boolean mCreated = false;

    /**
     * Activity是否已经创建
     *
     * @return mCreated
     */
    public boolean isCreated() {
        return mCreated;
    }

    /**
     * 时间监听器的Helper
     */
    private UIEventListener.Helper mEventListenerHelper = new UIEventListener.Helper();

    /**
     * 创建新的事件监听器
     * @param listener
     * @return
     */
    public UIEventListener createUIEventListener(EventListener listener) {
        return mEventListenerHelper.createUIEventListener(listener);
    }

    /**
     * 添加时间监听器
     * @param id
     * @param listener
     */
    public void addUIEventListener(EventId id, EventListener listener) {
        mEventListenerHelper.add(id, listener);
    }

    /**
     * 移除时间监听器
     * @param listener
     */
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
