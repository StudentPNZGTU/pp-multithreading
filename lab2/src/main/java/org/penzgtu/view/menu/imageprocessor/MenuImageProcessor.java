package org.penzgtu.view.menu.imageprocessor;

import io.bretty.console.view.MenuView;
import org.penzgtu.service.cache.FileCacheManagerService;
import org.penzgtu.service.imageprocessor.ImageProcessorService;
import org.penzgtu.view.menu.FileCacheConsoleManager;

public class MenuImageProcessor extends MenuView {

    public MenuImageProcessor(String runningTitle, String nameInParentMenu, ImageProcessorService imageProcessorService,
                              FileCacheManagerService fileCacheManagerService) {
        super(runningTitle, nameInParentMenu);
        addMenuItem(new ActionWatermark("Создать водяной знак", "add watermark to image",
                imageProcessorService)
        );
        addMenuItem(new ActionConvertFormat("Поменять формат изображения", "change image format",
                imageProcessorService)
        );
        addMenuItem(new ActionResizeImage("Изменить размер изображения", "change size image",
                imageProcessorService)
        );
        addMenuItem(new FileCacheConsoleManager("Работа с кэшем изображений", "Cache images",
                fileCacheManagerService)
        );
    }
}
