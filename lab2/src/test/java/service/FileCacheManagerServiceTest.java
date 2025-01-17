package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.penzgtu.service.cache.FileCacheManagerService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class FileCacheManagerServiceTest {
    private FileCacheManagerService cacheManager;
    private static final int THREAD_COUNT = 10;

    @BeforeEach
    void setUp() {
        cacheManager = new FileCacheManagerService();
    }

    @Test
    @DisplayName("Should handle concurrent cache operations safely")
    void shouldHandleConcurrentCacheOperations() throws InterruptedException {
        // Arrange
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch completionLatch = new CountDownLatch(THREAD_COUNT);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        // Act
        for (int i = 0; i < THREAD_COUNT; i++) {
            final String key = "key" + i;
            final byte[] data = ("data" + i).getBytes();

            executor.submit(() -> {
                try {
                    startLatch.await(); // Wait for all threads to be ready
                    cacheManager.cacheFile(key, data);
                    byte[] cached = cacheManager.getCachedFile(key);
                    assertArrayEquals(data, cached, "Cached data should match original");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    completionLatch.countDown();
                }
            });
        }

        // Start all threads simultaneously
        startLatch.countDown();

        // Assert
        assertTrue(completionLatch.await(5, TimeUnit.SECONDS), "All operations should complete");
        assertEquals(THREAD_COUNT, cacheManager.getFileCache().size(), "Cache should contain all entries");
    }

    @Test
    @DisplayName("Should handle concurrent cache clear operations")
    void shouldHandleConcurrentCacheClear() throws InterruptedException {
        // Arrange
        CountDownLatch completionLatch = new CountDownLatch(THREAD_COUNT);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        // Pre-populate cache
        for (int i = 0; i < THREAD_COUNT; i++) {
            cacheManager.cacheFile("key" + i, ("data" + i).getBytes());
        }

        // Act
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                try {
                    cacheManager.clearCache();
                } finally {
                    completionLatch.countDown();
                }
            });
        }
        assertTrue(completionLatch.await(5, TimeUnit.SECONDS), "All clear operations should complete");
        assertTrue(cacheManager.getFileCache().isEmpty(), "Cache should be empty after clear operations");
    }
}