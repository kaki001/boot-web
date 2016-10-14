package com.caimao.jyt.ashare.reactor.register;

import com.caimao.jyt.ashare.reactor.base.TaskEventConsumerRegister;
import com.caimao.jyt.ashare.reactor.base.TaskEventReactor;
import com.caimao.jyt.ashare.reactor.consumer.TestEventConsumer;
import com.caimao.jyt.ashare.reactor.event.TestEvent;
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
