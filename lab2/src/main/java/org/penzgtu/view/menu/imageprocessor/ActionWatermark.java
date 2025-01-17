package org.penzgtu.view.menu.imageprocessor;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.penzgtu.service.imageprocessor.ImageProcessorService;
import org.penzgtu.view.menu.imageprocessor.strategy.ActionImageStrategy;
import org.penzgtu.view.menu.imageprocessor.strategy.DefaultActionImageProcessor;

import java.io.File;
import java.io.IOException;

public class ActionWatermark extends DefaultActionImageProcessor implements ActionImage, ActionImageStrategy {

    protected String pathWatermarkFile;

    private static final Logger LOGGER = LogManager.getLogger(ActionWatermark.class);

    public ActionWatermark(String runningTitle, String nameInParentMenu,
                           ImageProcessorService imageProcessorService) {
        super(runningTitle, nameInParentMenu, imageProcessorService);
    }

    @Override
    public void execute() {
        this.runFolderViewer();
        this.pathWatermarkFile = this.prompt("Введите путь водяного знака: ", String.class);
        this.runImageViewer(this.pathWatermarkFile);

        try {
            this.imageProcessorService.addWatermark(new File(this.defaultPathInputFile),
                    new File(this.pathWatermarkFile), this.defaultPathOutputFile);
        } catch (IOException e) {
            LOGGER.error("Couldn't create watermark", e);
        }
    }
}
