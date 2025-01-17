package org.penzgtu.service.backup;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackupScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public void scheduleBackup(Runnable backupTask, long initialDelay, long period, TimeUnit unit) {
        scheduler.scheduleAtFixedRate(backupTask, initialDelay, period, unit);
    }
    public void stopScheduler() {
        scheduler.shutdown();
    }
}