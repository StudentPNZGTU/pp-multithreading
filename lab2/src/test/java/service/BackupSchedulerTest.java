package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.penzgtu.service.backup.BackupScheduler;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.*;
import java.nio.file.*;
import java.io.*;

class BackupSchedulerTest {
    private BackupScheduler backupScheduler;
    private Path testSourceDir;
    private Path testBackupDir;
    private volatile boolean taskExecuted;

    @BeforeEach
    void setUp() throws IOException {
        backupScheduler = new BackupScheduler();
        testSourceDir = Files.createTempDirectory("source");
        testBackupDir = Files.createTempDirectory("backup");
        taskExecuted = false;

        // Create test files
        Files.writeString(testSourceDir.resolve("test1.txt"), "test content 1");
        Files.writeString(testSourceDir.resolve("test2.txt"), "test content 2");
    }

    @Test
    @DisplayName("Should execute scheduled backup task at fixed rate")
    void shouldExecuteScheduledBackupTask() throws InterruptedException {
        // Arrange
        CountDownLatch latch = new CountDownLatch(2);
        Runnable testTask = () -> {
            taskExecuted = true;
            latch.countDown();
        };

        // Act
        backupScheduler.scheduleBackup(testTask, 0, 1, TimeUnit.SECONDS);

        assertTrue(latch.await(3, TimeUnit.SECONDS), "Task should execute twice within 3 seconds");
        assertTrue(taskExecuted, "Backup task should have been executed");
    }

    @Test
    @DisplayName("Should properly shutdown scheduler")
    void shouldProperlyShutdownScheduler() throws InterruptedException {
        // Arrange
        CountDownLatch latch = new CountDownLatch(1);
        Runnable testTask = latch::countDown;

        // Act
        backupScheduler.scheduleBackup(testTask, 0, 1, TimeUnit.SECONDS);
        backupScheduler.stopScheduler();
        assertFalse(latch.await(2, TimeUnit.SECONDS), "No tasks should execute after shutdown");
    }
}
