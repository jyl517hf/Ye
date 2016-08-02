package com.eaotin.framework.base;

import android.os.Handler;

import com.eaotin.framework.event.EventArgs;
import com.eaotin.framework.event.EventCenter;
import com.eaotin.framework.event.EventId;
import com.eaotin.framework.event.EventListener;
import com.eaotin.framework.event.StatusEventArgs;

/**
 * BaseLogic.java是用于逻辑访问的基类
 *
 * @author 金玉龙
 * @version 2.0 16/8/2
 * @modify
 */
public class BaseLogic {
    /**
     * 通过handler将事件返回给主线程
     */
    protected Handler handler = new Handler();

    /**
     * 无返回体,只有返回码时使用该方法将事件发送到UI层
     *
     * @param listener
     * @param resultCode
     */
    protected void fireEventWithResultCode(final EventListener listener, int resultCode) {
        fireEventWithArgs(listener, new StatusEventArgs(resultCode));
    }

    protected void fireEventWithEventId(final EventId eventId, final EventArgs args) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                EventCenter.self().fireEvent(eventId, args);
            }
        });
    }

    /**
     * 有返回体时使用该方法将时间发送到UI层
     *
     * @param listener
     * @param args
     */
    protected void fireEventWithArgs(final EventListener listener, final EventArgs args) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onEvent(EventId.eSingle, args);
            }
        });
    }

}
