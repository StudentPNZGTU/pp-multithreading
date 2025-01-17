package org.penzgtu.view.menu;

import io.bretty.console.view.MenuView;
import org.penzgtu.service.cache.FileCacheManagerService;
import org.penzgtu.service.backup.BackupService;
import org.penzgtu.service.backup.BackupScheduler;
import org.penzgtu.service.filecopy.FileCopyManagerService;
import org.penzgtu.service.imageprocessor.ImageProcessorService;
import org.penzgtu.view.menu.imageprocessor.MenuImageProcessor;

public class MenuTasks extends MenuView {
    public MenuTasks(String runningTitle, String nameInParentMenu, ImageProcessorService imageProcessorService,
                     FileCopyManagerService fileCopyManagerService, BackupService backupService,
                     BackupScheduler backupScheduler, FileCacheManagerService fileCacheManagerService) {
        super(runningTitle, nameInParentMenu);
        this.addMenuItem(new MenuImageProcessor("Работа с изображениями", "Image Processor Service",
                imageProcessorService, fileCacheManagerService)
        );
        this.addMenuItem(new BackupConsoleManager("Резервное копирование файла(ов)", "Backup Service",
                backupService, backupScheduler)
        );
        this.addMenuItem(new FileCopyConsoleManager("Множественное копирование", "CopyManager Service",
                fileCopyManagerService)
        );
        this.addMenuItem(new RecursiveFileCounterConsoleManager("Вычисление рекурсивно суммы ряда", "RecursiveSum Service"));
        this.println("-----------------------------------------------------------------------------------------------");
        this.println(getDescription());
        this.println("-----------------------------------------------------------------------------------------------");
    }

    public String getDescription() {
        return "Постановка задачи:\n" +
                "В соответствии с вариантом выполните следующее основное задание:\n" +
                "1. Создайте новый проект в среде программирования.\n" +
                "2. Реализуйте с помощью ThreadPoolExecutor выполнение параллельной обработки изображений. " +
                "Например, изменить их размер, добавить водяной знак или сохранить в другой формат.\n" +
                "3. Реализуйте возможность резервного копирования файлов с определенной периодичностью с помощью " +
                "ScheduledThreadPoolExecutor.\n" +
                "4. Реализуйте копирование группы файлов с помощью CachedThreadPoolExecutor.\n" +
                "5. Реализуйте рекурсивное вычисление суммы ряда с помощью ForkJoinPool.\n" +
                "6. Подготовьте отчет о выполнении лабораторной работы.";

    }
}
