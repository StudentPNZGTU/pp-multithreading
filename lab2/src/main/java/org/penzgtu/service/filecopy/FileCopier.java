package org.penzgtu.service.filecopy;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import org.penzgtu.service.ThreadManager;


public class FileCopier {
    private static final Logger LOGGER = LogManager.getLogger(FileCopier.class);
    private final ThreadManager threadManager;

    public FileCopier(ThreadManager threadManager) {
        this.threadManager = threadManager;
    }

    public void copyFiles(List<Path> files, Path targetDir) {
        for (Path file : files) {
            threadManager.getExecutor().submit(() -> copyFile(file, targetDir));
        }
    }

    public static void copyFile(Path file, Path targetDir) {
        try {
            Path target = targetDir.resolve(file.getFileName());
            Files.createDirectories(target.getParent());
            Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("Copied: " + file + " to " + target);
        } catch (IOException e) {
            LOGGER.error("File copying error", e);
        }
    }
}