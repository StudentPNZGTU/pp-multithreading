package org.penzgtu.service.backup;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.penzgtu.service.filecopy.FileCopier;

import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Stream;

public class BackupService {
    private Path sourceDir;
    private Path backupDir;
    private BackupCounter backupCounter;

    private final Logger LOGGER = LogManager.getLogger(BackupService.class);

    public BackupService(Path sourceDir, Path backupDir) {
        this.sourceDir = sourceDir;
        this.backupDir = backupDir;
        if (backupDir != null) {
            this.backupCounter = new BackupCounter(backupDir);
        }
    }

    public void backupFiles() {
        int currentCount = backupCounter.getNextBackupNumber();
        Path currentBackupDir = backupDir.resolve("backup_" + currentCount);

        try (Stream<Path> paths = Files.walk(sourceDir)) {
            paths.filter(Files::isRegularFile)
                    .forEach(file -> FileCopier.copyFile(file, currentBackupDir));
        } catch (IOException e) {
            LOGGER.error("Error copying files by the backup service {}", e);
        }
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = Path.of(sourceDir);
    }

    public void setBackupDir(String backupDir) {
        Path pathBackupDir = Path.of(backupDir);
        this.backupDir = pathBackupDir;
        this.backupCounter = new BackupCounter(pathBackupDir);
    }
}
