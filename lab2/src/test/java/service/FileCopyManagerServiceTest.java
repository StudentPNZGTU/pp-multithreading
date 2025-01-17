package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.penzgtu.service.filecopy.FileCopyManagerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class FileCopyManagerServiceTest {
    private FileCopyManagerService copyManager;
    private Path sourceDir;
    private Path targetDir;
    private List<Path> testFiles;

    @BeforeEach
    void setUp() throws IOException {
        copyManager = new FileCopyManagerService();
        sourceDir = Files.createTempDirectory("source");
        targetDir = Files.createTempDirectory("target");
        testFiles = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Path file = sourceDir.resolve("test" + i + ".txt");
            Files.writeString(file, "test content " + i);
            testFiles.add(file);
        }
    }

    @Test
    @DisplayName("Should copy multiple files concurrently")
    void shouldCopyMultipleFilesConcurrently() throws InterruptedException {
        // Arrange
        CountDownLatch completionLatch = new CountDownLatch(1);

        // Act
        copyManager.copyFiles(testFiles, targetDir);
        completionLatch.await(5, TimeUnit.SECONDS); // Give time for async operations

        for (Path sourceFile : testFiles) {
            Path targetFile = targetDir.resolve(sourceFile.getFileName());
            assertTrue(Files.exists(targetFile), "Target file should exist: " + targetFile);

            try {
                String sourceContent = Files.readString(sourceFile);
                String targetContent = Files.readString(targetFile);
                assertEquals(sourceContent, targetContent, "File contents should match");
            } catch (IOException e) {
                fail("Failed to read file contents: " + e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Should handle concurrent shutdown properly")
    void shouldHandleConcurrentShutdown() throws InterruptedException {
        // Arrange
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch copyStartedLatch = new CountDownLatch(1);
        CountDownLatch shutdownCompleteLatch = new CountDownLatch(1);

        // Act
        executor.submit(() -> {
            copyManager.copyFiles(testFiles, targetDir);
            copyStartedLatch.countDown();
        });

        executor.submit(() -> {
            try {
                copyStartedLatch.await(); // Wait for copy to start
                copyManager.shutdown();
                shutdownCompleteLatch.countDown();
            } catch (InterruptedException e) {
                fail("Test interrupted: " + e.getMessage());
            }
        });
        assertTrue(shutdownCompleteLatch.await(5, TimeUnit.SECONDS), "Shutdown should complete");
        executor.shutdown();
    }
}
