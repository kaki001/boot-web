package com.geh.reactor.consumer;

import com.geh.reactor.base.AbstractTaskEventConsumer;
import com.geh.reactor.event.TestEvent;
import org.springframework.stereotype.Component;

@Component
public class TestEventConsumer extends AbstractTaskEventConsumer<TestEvent> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(TestEvent e) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("************************************ exe ************************************ : " + e.getUid());
    }
}
