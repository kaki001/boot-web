package com.geh.reactor.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.bus.selector.ClassSelector;
import reactor.core.Dispatcher;
import reactor.core.dispatch.RingBufferDispatcher;
import reactor.core.dispatch.WorkQueueDispatcher;
import reactor.fn.Consumer;

import java.util.ArrayList;
import java.util.List;


public class TaskEventReactor implements AutoCloseable {

    private static final Logger logger = LoggerFactory.getLogger(TaskEventReactor.class);

    private static final TaskReactorConfig DEFAULT_CONFIG = new TaskReactorConfig();

    private TaskReactorConfig config;

    private EventBus bus;

    private Consumer<Throwable> throwableConsumer = new LogThrowableConsumer();

    private List<TaskEventConsumerRegister> taskEventConsumerRegisters = new ArrayList<>();

    public void setThrowableConsumer(Consumer<Throwable> throwableConsumer) {
        this.throwableConsumer = throwableConsumer;
    }

    public void setTaskEventConsumerRegisters(List<TaskEventConsumerRegister> taskEventConsumerRegisters) {
        this.taskEventConsumerRegisters = taskEventConsumerRegisters;
    }

    public TaskEventReactor(final TaskReactorConfig config) {
        this.config = config;
        Dispatcher dispatcher = getDispatcher(config);
        this.bus = EventBus.create(new Environment().assignErrorJournal(this.throwableConsumer), dispatcher);
    }

    private Dispatcher getDispatcher(final TaskReactorConfig config) {
        switch (config.getDispatcherType()) {
            case THREAD_POOL_EXECUTOR:
                return new WorkQueueDispatcher(config.getReactorName(),
                        config.getThreadPoolNum(),
                        config.getBakLogSize(), this.throwableConsumer,
                        config.getProducerType(),
                        config.getWaitStrategy());
            case RING_BUFFER:
                return new RingBufferDispatcher(config.getReactorName(),
                        config.getBakLogSize(),
                        this.throwableConsumer,
                        config.getProducerType(),
                        config.getWaitStrategy());
            default:
                throw new RuntimeException("Can't support dispatcher type:" + config.getDispatcherType().name());
        }
    }

    public TaskEventReactor() {
        this(DEFAULT_CONFIG);
    }

    public <T extends TaskEvent> void registerEventConsumer(final Class<T> cls, final AbstractTaskEventConsumer<T> processor) {
        this.bus.on(new ClassSelector(cls), processor);
    }

    public <T extends TaskEvent> void addEvent(final T e) {
        final Event<T> wrap = Event.wrap(e);
        this.bus.notify(e.getClass(), wrap);
    }

    /**
     * 初始化工作
     */
    public void initialize() {
        logger.debug("Start initializing reactor {}.", this.config.getReactorName());
        for (final TaskEventConsumerRegister taskEventConsumerRegister : this.taskEventConsumerRegisters) {
            taskEventConsumerRegister.registerEventConsumer(this);
        }
    }

    /**
     * 关闭工作
     */
    public void shutdown() {
        try {
            this.bus.getDispatcher().shutdown();
        } catch (final Exception e) {
            logger.error("shutdown reactor {} error:{}", this.config.getReactorName(), e);
        }
    }

    /**
     * 关闭
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        shutdown();
    }

    private static class LogThrowableConsumer implements Consumer<Throwable> {
        @Override
        public void accept(final Throwable throwable) {
            logger.error("reactor error:{}", throwable.getMessage(), throwable);
        }
    }
}
