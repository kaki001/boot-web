package com.geh.reactor.base;

import reactor.core.config.DispatcherType;
import reactor.jarjar.com.lmax.disruptor.BlockingWaitStrategy;
import reactor.jarjar.com.lmax.disruptor.WaitStrategy;
import reactor.jarjar.com.lmax.disruptor.dsl.ProducerType;

/**
 * Created on 16/8/8.
 *
 * @author hutao
 * @version 1.0
 */
public class TaskReactorConfig {
    private final static int DEFAULT_EVENT_BAK_LOG_SIZE = 4096;
    private final static int DEFAULT_THREAD_POOL_NUM = 32;
    private static final String DEFAULT_REACTOR_NAME = "task reactor";
    private String reactorName = DEFAULT_REACTOR_NAME;
    private int threadPoolNum = DEFAULT_THREAD_POOL_NUM;
    private int bakLogSize = DEFAULT_EVENT_BAK_LOG_SIZE;
    private WaitStrategy waitStrategy = new BlockingWaitStrategy();
    private ProducerType producerType = ProducerType.MULTI;
    private DispatcherType dispatcherType = DispatcherType.THREAD_POOL_EXECUTOR;

    public ProducerType getProducerType() {
        return this.producerType;
    }

    public TaskReactorConfig setProducerType(final ProducerType producerType) {
        this.producerType = producerType;
        return this;
    }

    public DispatcherType getDispatcherType() {
        return this.dispatcherType;
    }

    public TaskReactorConfig setDispatcherType(final DispatcherType dispatcherType) {
        this.dispatcherType = dispatcherType;
        return this;
    }

    public int getThreadPoolNum() {
        return this.threadPoolNum;
    }

    public TaskReactorConfig setThreadPoolNum(final int threadPoolNum) {
        this.threadPoolNum = threadPoolNum;
        return this;
    }

    public String getReactorName() {
        return this.reactorName;
    }

    public TaskReactorConfig setReactorName(final String reactorName) {
        this.reactorName = reactorName;
        return this;
    }

    public int getBakLogSize() {
        return this.bakLogSize;
    }

    public TaskReactorConfig setBakLogSize(final int bakLogSize) {
        this.bakLogSize = bakLogSize;
        return this;
    }

    public WaitStrategy getWaitStrategy() {
        return this.waitStrategy;
    }

    public TaskReactorConfig setWaitStrategy(final WaitStrategy waitStrategy) {
        this.waitStrategy = waitStrategy;
        return this;
    }
}
