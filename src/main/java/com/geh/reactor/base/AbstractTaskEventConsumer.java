package com.geh.reactor.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.bus.Event;
import reactor.fn.Consumer;

public abstract class AbstractTaskEventConsumer<T extends TaskEvent> implements Consumer<Event<T>> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractTaskEventConsumer.class);

    @Override
    public final void accept(final Event<T> e) {
        process(e.getData());
    }

    /**
     * 处理事件
     *
     * @param data
     */
    protected abstract void process(final T data);
}
