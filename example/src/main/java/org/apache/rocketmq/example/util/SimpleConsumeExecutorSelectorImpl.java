package org.apache.rocketmq.example.util;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.rocketmq.client.impl.consumer.ConsumeExecutorSelector;
import org.apache.rocketmq.common.ThreadFactoryImpl;
import org.apache.rocketmq.common.message.MessageExt;

public class SimpleConsumeExecutorSelectorImpl implements ConsumeExecutorSelector {

    private final ThreadPoolExecutor executor;

    public SimpleConsumeExecutorSelectorImpl() {
        this.executor = new ThreadPoolExecutor(
            20,
            64,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new ThreadFactoryImpl("SimpleConsumeMessageThread_")
        );
    }

    @Override public ExecutorService selectExecutor(List<MessageExt> msgs) {
        return this.executor;
    }

    @Override public int getCorePoolSize() {
        return executor.getCorePoolSize();
    }

    @Override public void shutdownUnderlyingExecutors() {
        this.executor.shutdown();
    }

}
