package com.eaotin.framework.event;

/**
 * 事件监听的listener的接口
 */
public interface EventListener {
    void onEvent(EventId eventId, EventArgs args);
}
