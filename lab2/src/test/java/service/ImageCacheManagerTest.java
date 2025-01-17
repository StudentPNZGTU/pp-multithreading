package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.penzgtu.service.cache.FileCacheManagerService;
import org.penzgtu.service.imageprocessor.ImageCacheManager;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ImageCacheManagerTest {
    private ImageCacheManager imageCacheManager;
    private FileCacheManagerService fileCacheManager;
    private static final int CONCURRENT_OPERATIONS = 5;

    @BeforeEach
    void setUp() {
        fileCacheManager = new FileCacheManagerService();
        imageCacheManager = new ImageCacheManager(fileCacheManager);
    }

    @Test
    @DisplayName("Should handle concurrent image caching")
    void shouldHandleConcurrentImageCaching() throws InterruptedException {
        // Arrange
        CountDownLatch completionLatch = new CountDownLatch(CONCURRENT_OPERATIONS);
        ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_OPERATIONS);
        BufferedImage[] testImages = new BufferedImage[CONCURRENT_OPERATIONS];

        // Create test images
        for (int i = 0; i < CONCURRENT_OPERATIONS; i++) {
            testImages[i] = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        }

        // Act
        for (int i = 0; i < CONCURRENT_OPERATIONS; i++) {
            final int index = i;
            executor.submit(() -> {
                try {
                    imageCacheManager.cacheBufferedImage(
                            testImages[index],
                            "png",
                            "test_image_" + index + ".png"
                    );
                } catch (IOException e) {
                    fail("Failed to cache image: " + e.getMessage());
                } finally {
                    completionLatch.countDown();
                }
            });
        }

        assertTrue(completionLatch.await(5, TimeUnit.SECONDS), "All caching operations should complete");
        assertEquals(CONCURRENT_OPERATIONS, fileCacheManager.getFileCache().size(),
                "Cache should contain all images");
    }

    @Test
    @DisplayName("Should handle concurrent image retrieval")
    void shouldHandleConcurrentImageRetrieval() throws InterruptedException {
        // Arrange
        CountDownLatch completionLatch = new CountDownLatch(CONCURRENT_OPERATIONS);
        ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_OPERATIONS);

        // Pre-populate cache
        BufferedImage testImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        try {
            imageCacheManager.cacheBufferedImage(testImage, "png", "test_image.png");
        } catch (IOException e) {
            fail("Failed to setup test: " + e.getMessage());
        }

        // Act
        for (int i = 0; i < CONCURRENT_OPERATIONS; i++) {
            executor.submit(() -> {
                try {
                    imageCacheManager.createImageFromCache("test_image.png");
                } finally {
                    completionLatch.countDown();
                }
            });
        }
        assertTrue(completionLatch.await(5, TimeUnit.SECONDS), "All retrieval operations should complete");
    }
}
