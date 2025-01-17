package org.penzgtu.view.menu.imageprocessor;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.penzgtu.service.imageprocessor.ImageProcessorService;
import org.penzgtu.utils.FileUtils;
import org.penzgtu.view.menu.imageprocessor.strategy.ActionImageStrategy;
import org.penzgtu.view.menu.imageprocessor.strategy.DefaultActionImageProcessor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ActionResizeImage extends DefaultActionImageProcessor implements ActionImage, ActionImageStrategy {

    private BufferedImage inputImage;

    private final ImageProcessorService imageProcessorService;

    private static final Logger LOGGER = LogManager.getLogger(ActionResizeImage.class);


    public ActionResizeImage(String runningTitle, String nameInParentMenu,
                             ImageProcessorService imageProcessorService) {
        super(runningTitle, nameInParentMenu, imageProcessorService);
        this.imageProcessorService = imageProcessorService;
    }

    @Override
    public void execute() {
        try {
            inputImage = imageProcessorService.getImageProcessor().
                    fileToBufferedImage(new File(this.defaultPathInputFile));
        } catch (IOException e) {
            LOGGER.error("Couldn't read the image: " +
                    FileUtils.extractFileName(this.defaultPathInputFile), e
            );
            this.onBack();
        }

        this.println("Ширина и длина исходного изображения изображения (" +
                inputImage.getWidth() + "X" + inputImage.getHeight() + ")");

        int width = this.prompt("Введите ширину изображения не больше " + inputImage.getWidth() + ": ",
                Integer.class, input -> input <= inputImage.getWidth());
        int height = this.prompt("Введите высоту изображения не больше "+ inputImage.getHeight() + ": ",
                Integer.class, input -> input <= inputImage.getHeight());

        try {
            this.imageProcessorService.resizeImage(new File(this.defaultPathInputFile),
                    this.defaultPathOutputFile, width, height);
        } catch (IOException e) {
            LOGGER.error("Couldn't reduce image size: " +
                    FileUtils.extractFileName(this.defaultPathInputFile), e
            );
        }
    }
}
