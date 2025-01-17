package org.penzgtu.service.backup;

import java.nio.file.*;
import java.util.regex.*;
import java.util.stream.Stream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class BackupCounter {
    private final AtomicInteger counter;

    private final Logger LOGGER = LogManager.getLogger(BackupCounter.class);


    public BackupCounter(Path backupDir) {
        this.counter = new AtomicInteger(initializeCounter(backupDir));
    }

    private int initializeCounter(Path backupDir) {
        try (Stream<Path> paths = Files.list(backupDir)) {
            Pattern pattern = Pattern.compile("backup_(\\d+)");
            return paths.filter(Files::isDirectory)
                .map(Path::getFileName)
                .map(Path::toString)
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .map(matcher -> Integer.parseInt(matcher.group(1)))
                .max(Integer::compareTo)
                .orElse(0);
        } catch (IOException e) {
            LOGGER.error("Error initializing BackupCounter", e);
            return 0;
        }
    }

    public int getNextBackupNumber() {
        return counter.incrementAndGet();
    }
}