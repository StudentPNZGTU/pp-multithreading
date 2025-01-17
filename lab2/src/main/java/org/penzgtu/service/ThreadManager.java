package org.penzgtu.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private final ExecutorService executor;

    public ThreadManager() {
        this.executor = Executors.newCachedThreadPool();
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void shutdown() {
        executor.shutdown();
    }
} 