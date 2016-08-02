package com.eaotin.framework.event;

import android.app.Activity;

import com.eaotin.framework.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * 界面层的EventListener的封装类，用于判断回调时界面是否还存在，不存在就不通知了
 */
public class UIEventListener implements EventListener {

    private Activity mActivity = null;
    private EventListener mListener = null;

    public UIEventListener(Activity activity, EventListener listener) {
        mActivity = activity;
        mListener = listener;
    }

    @Override
    public void onEvent(EventId eventId,EventArgs args) {
        if (mActivity != null) {
            if ( mActivity.getClass()==BaseActivity.class && !((BaseActivity)mActivity).isCreated()) {
                return;
            }
        }
        mListener.onEvent(eventId,args);
    }

    public static class Helper {
        private Map<EventListener, UIEventListener> mContainer = new HashMap<EventListener, UIEventListener>();

        private Activity mActivity = null;


        public void setHost(Activity value) {
            mActivity = value;
        }


        public UIEventListener createUIEventListener(EventListener listener) {
            if (mActivity != null) {
                return new UIEventListener(mActivity, listener);
            }
            return null;
        }

        public void add(EventId id, EventListener listener) {
            UIEventListener uiListener = createUIEventListener(listener);
            EventCenter.self().addListener(id, uiListener);
            mContainer.put(listener, uiListener);
        }

        public void remove(EventListener listener) {
            if (mContainer.containsKey(listener)) {
                UIEventListener uiListener = mContainer.get(listener);
                EventCenter.self().removeListener(uiListener);
                mContainer.remove(listener);
            }
        }

        public void clear() {
            for (UIEventListener uiListener : mContainer.values()) {
                EventCenter.self().removeListener(uiListener);
            }
            mContainer.clear();
        }
    }
}
