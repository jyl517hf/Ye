package com.eaotin.framework.event;

public interface EventListener {
    void onEvent(EventId id, EventArgs args);
}
