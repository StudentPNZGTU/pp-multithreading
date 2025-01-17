package org.penzgtu.view.menu.imageprocessor;

import io.bretty.console.view.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.penzgtu.service.imageprocessor.ImageProcessorService;
import org.penzgtu.view.menu.imageprocessor.strategy.ActionImageStrategy;
import org.penzgtu.view.menu.imageprocessor.strategy.DefaultActionImageProcessor;

import java.io.File;
import java.io.IOException;

public class ActionConvertFormat extends DefaultActionImageProcessor implements ActionImage, ActionImageStrategy {

    private final ImageProcessorService imageProcessorService;

    private static final Logger LOGGER = LogManager.getLogger(ActionConvertFormat.class);

    public ActionConvertFormat(String runningTitle, String nameInParentMenu,
            ImageProcessorService imageProcessorService) {
        super(runningTitle, nameInParentMenu, imageProcessorService);
        this.imageProcessorService = imageProcessorService;
    }

    @Override
    public void execute() {
        this.println("Список поддерживаемых форматов:");

        for (Formats value : Formats.values()) {
            this.print(value.name() + " ");
        }
        this.println();

        Validator<String> validator = format -> {
            for (Formats value : Formats.values()) {
                if (value.name().contains(format)) {
                    return true;
                }
            }
            return false;
        };

        String format = this.prompt("Введите новый формат изображения: ", String.class, validator);

        try {
            this.imageProcessorService.changeImageFormat(new File(this.defaultPathInputFile), format,
                    this.defaultPathOutputFile
            );
        } catch (IOException e) {
            LOGGER.error("Couldn't create image format", e);
        }

    }
}

/*
    Библиотека thumbnailator поддерживает больше форматов
 */
enum Formats {
    jpeg,
    png,
    bmp
}