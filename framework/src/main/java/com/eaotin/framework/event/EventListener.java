package com.eaotin.framework.event;

public interface EventListener {
    void onEvent(EventId eventId, EventArgs args);
}
