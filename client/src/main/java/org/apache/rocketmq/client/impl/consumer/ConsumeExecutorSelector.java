/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.client.impl.consumer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * {@link java.util.concurrent.ExecutorService} selector for consumer, an alternative of {@code consumeExecutor}.
 * <p>
 * It could be used to do {@code ExecutorService} isolation for different {@code MessageExt}, isolated by topic, tag or
 * something else.
 * <p>
 * The implementation MUST has a no-arg constructor.
 */
public interface ConsumeExecutorSelector {

    /**
     * Select an {@link java.util.concurrent.ExecutorService} instance to consume {@code MessageExt}.
     *
     * @param msgs Just received messages
     * @return Instance of {@code ExecutorService}, it could NOT be null.
     */
    ExecutorService selectExecutor(List<MessageExt> msgs);

    /**
     * Get underlying executors' core pool size.
     *
     * @return Total core pool size
     */
    int getCorePoolSize();

    /**
     * Shutdown all underlying executors.
     */
    void shutdownUnderlyingExecutors();

}
