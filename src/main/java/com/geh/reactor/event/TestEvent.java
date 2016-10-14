package com.geh.reactor.event;


import com.geh.reactor.base.TaskEvent;

public class TestEvent implements TaskEvent {

    private long uid;

    public TestEvent(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
