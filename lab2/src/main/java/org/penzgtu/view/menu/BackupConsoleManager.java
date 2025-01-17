package org.penzgtu.view.menu;

import io.bretty.console.view.ActionView;
import org.penzgtu.service.backup.BackupService;
import org.penzgtu.service.backup.BackupScheduler;
import org.penzgtu.utils.FileUtils;
import org.penzgtu.view.swing.FolderViewer;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class BackupConsoleManager extends ActionView {

    private final BackupService backupService;
    private final BackupScheduler backupScheduler;

    public BackupConsoleManager(String runningTitle, String nameInParentMenu, BackupService backupService,
                                BackupScheduler backupScheduler) {
        super(runningTitle, nameInParentMenu);
        this.backupService = backupService;
        this.backupScheduler = backupScheduler;
    }

    @Override
    public void executeCustomAction() {
        long initialDelay = this.prompt("Введите стартовую задержку в сек: ", Long.class);
        long secPeriod = this.prompt("Введите период в сек: ", Long.class);

        FolderViewer.runFolderViewer();
        backupService.setSourceDir(inputDirectory("Введите директорию файлов: "));

        FolderViewer.runFolderViewer();
        backupService.setBackupDir(inputDirectory("Введите директорию для бэкапа: "));

        backupScheduler.scheduleBackup(backupService::backupFiles, initialDelay, secPeriod, TimeUnit.SECONDS);
    }

    public String inputDirectory(String prompt) {
        return this.prompt(prompt, String.class,
                path -> FileUtils.directoryExists(Path.of(path)));
    }
}
