package com.caimao.jyt.ashare.reactor.consumer;

import com.caimao.jyt.ashare.reactor.base.AbstractTaskEventConsumer;
import com.caimao.jyt.ashare.reactor.event.TestEvent;
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
