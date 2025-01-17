package org.penzgtu.service.imageprocessor;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class ImageProcessor {
    private static final Logger LOGGER = LogManager.getLogger(ImageProcessor.class);

    public BufferedImage addWatermark(BufferedImage inputImage, File watermarkFile) throws IOException {
        BufferedImage watermarkImage = Thumbnails.of(watermarkFile).scale(0.5).asBufferedImage();
        if (watermarkImage == null) {
            LOGGER.error("Failed to read watermark image: " + watermarkFile.getPath());
        }

        return Thumbnails.of(inputImage)
                .size(inputImage.getWidth(), inputImage.getHeight())
                .watermark(Positions.BOTTOM_RIGHT, watermarkImage, 0.5f)
                .asBufferedImage();
    }

    public BufferedImage resizeImage(BufferedImage inputImage, int width, int height) throws IOException {
        return Thumbnails.of(inputImage).size(width, height).asBufferedImage();
    }

    public BufferedImage fileToBufferedImage(File inputFile) throws IOException {
        return ImageIO.read(inputFile);
    }
} 