package org.penzgtu.view.menu;

import io.bretty.console.view.ActionView;
import io.bretty.console.view.Validator;
import org.penzgtu.service.cache.FileCacheManagerService;

public class FileCacheConsoleManager extends ActionView {

    private final FileCacheManagerService fileCacheManagerService;

    public FileCacheConsoleManager(String runningTitle, String nameInParentMenu,
                                   FileCacheManagerService fileCacheManagerService) {
        super(runningTitle, nameInParentMenu);
        this.fileCacheManagerService = fileCacheManagerService;
    }

    @Override
    public void executeCustomAction() {

        this.println("Временные файлы изображений хранящиеся в кэше (FileCacheManager):");
        this.println("-----------------------------------------------------------------------------------------------");
        this.println("< Path Image > : < Cache.length() (Bytes) >");
        for (String path: this.fileCacheManagerService.getFileCache().keySet()) {
            this.println("< " + path + " > : < " + this.fileCacheManagerService.getCachedFile(path).length + " >");
        }

        Validator<Integer> inputValidator = input -> input > 0 && input < 5;
        this.println("-----------------------------------------------------------------------------------------------");
        this.println("1) Создать все изображения\n2) Создать конкретное изображение\n3) Очистить кэш\n4) Назад");

        int choice = this.prompt("Please enter a number to continue: ", Integer.class, inputValidator);

        switch (choice) {
            case 1 -> this.fileCacheManagerService.createAllFilesFromCache();
            case 2 -> this.fileCacheManagerService.createFileFromCache(
                    this.prompt("Введите путь из кэша:", String.class)
            );
            case 3 -> this.fileCacheManagerService.clearCache();
            case 4 -> this.onBack();
        }
    }
}
