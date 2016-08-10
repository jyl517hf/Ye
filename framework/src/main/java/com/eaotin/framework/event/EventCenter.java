package com.eaotin.framework.event;

import com.eaotin.framework.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件中心类,用于处理所有事件的发送以及传递,该类是单例的
 */
public class EventCenter {

    private EventCenter() {

    }

    private static EventCenter ins = new EventCenter();

    public static EventCenter self() {
        return ins;
    }

    /**
     * 时间打包类的List集合
     */
    private List<EventListenerPackage> mListeners = new ArrayList<>();

    /**
     * 添加全局监听器
     *
     * @param value
     */
    public void addListener(EventListener value) {
        synchronized (this.mListeners) {
            this.mListeners.add(new EventListenerPackage(EventId.eAll, value));
        }
    }

    /**
     * 添加监听器
     *
     * @param id
     * @param listener
     * @remark 不会重复添加
     */
    public void addListener(EventId id, EventListener listener) {
        synchronized (this.mListeners) {
            if (!hasListener(id, listener)) {
                this.mListeners.add(new EventListenerPackage(id, listener));
            }
        }
    }

    /**
     * 移除监听器
     *
     * @param value
     */
    public void removeListener(EventListener value) {
        synchronized (mListeners) {
            List<EventListenerPackage> listeners = null;
            listeners = ListUtil.clone(mListeners);
            for (EventListenerPackage pack : listeners) {
                if (pack.listener == value) {
                    mListeners.remove(pack);
                }
            }
        }
    }

    /**
     * 移除监听器
     *
     * @param id
     * @param listener
     */
    public void removeListener(EventId id, EventListener listener) {
        synchronized (mListeners) {
            List<EventListenerPackage> listeners = null;
            listeners = ListUtil.clone(mListeners);
            for (EventListenerPackage pack : listeners) {
                if (pack.id == id) {
                    if (pack.listener == listener) {
                        mListeners.remove(pack);
                        //移除成功后跳出
                        break;
                    }
                }
            }
        }
    }

    /**
     * 触发事件
     *
     * @param id
     * @param args
     */
    public void fireEvent(EventId id, EventArgs args) {
        List<EventListenerPackage> listeners = null;
        synchronized (mListeners) {
            listeners = ListUtil.clone(mListeners);
        }
        for (EventListenerPackage pack : listeners) {
            if (pack.id == EventId.eAll || pack.id == id) {
                pack.listener.onEvent(id, args);
            }
        }
    }

    /**
     * 通过事件id和事件监听器判定是否已经存在了该事件的监听
     *
     * @param id
     * @param listener
     * @return
     */
    private boolean hasListener(EventId id, EventListener listener) {
        for (EventListenerPackage pack : mListeners) {
            if (pack.id == id && pack.listener == listener) {
                return true;
            }
        }
        return false;
    }

    /**
     * 事件包的内部类,包含事件id和事件监听器
     */
    private static class EventListenerPackage {
        public EventListenerPackage(EventId id, EventListener listener) {
            this.id = id;
            this.listener = listener;
        }

        private EventId id;
        private EventListener listener;
    }
}




