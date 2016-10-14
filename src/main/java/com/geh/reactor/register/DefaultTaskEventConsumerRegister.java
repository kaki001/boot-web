package com.geh.reactor.register;

import com.geh.reactor.base.TaskEventConsumerRegister;
import com.geh.reactor.base.TaskEventReactor;
import com.geh.reactor.consumer.TestEventConsumer;
import com.geh.reactor.event.TestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 16/8/10.
 *
 * @author hutao
 * @version 1.0
 */
@Component
public class DefaultTaskEventConsumerRegister implements TaskEventConsumerRegister {

    @Autowired
    private TestEventConsumer testEventConsumer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerEventConsumer(final TaskEventReactor taskEventReactor) {
        taskEventReactor.registerEventConsumer(TestEvent.class, this.testEventConsumer);

    }
}
