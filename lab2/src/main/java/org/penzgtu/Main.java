package org.penzgtu;

import org.penzgtu.service.backup.BackupScheduler;
import org.penzgtu.service.backup.BackupService;
import org.penzgtu.service.cache.FileCacheManagerService;
import org.penzgtu.service.filecopy.FileCopyManagerService;
import org.penzgtu.service.imageprocessor.ImageProcessorService;
import org.penzgtu.view.menu.MenuTasks;


public class Main {
    public static void main(String[] args) {

        FileCacheManagerService fileCacheManager = new FileCacheManagerService();
        FileCopyManagerService fileCopyManager = new FileCopyManagerService();
        BackupService backupService = new BackupService(null, null);
        ImageProcessorService imageProcessor = new ImageProcessorService(fileCacheManager);
        BackupScheduler backupScheduler = new BackupScheduler();

        MenuTasks rootMenu = new MenuTasks("Menu", "rootMenu",
                imageProcessor, fileCopyManager, backupService, backupScheduler, fileCacheManager
        );
        rootMenu.display();
    }
}