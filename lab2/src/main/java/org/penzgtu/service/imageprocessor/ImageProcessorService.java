package org.penzgtu.service.imageprocessor;

import org.penzgtu.service.cache.FileCacheManagerService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.penzgtu.utils.FileUtils;

public class ImageProcessorService {
    private static final Logger LOGGER = LogManager.getLogger(ImageProcessorService.class);
    private final ImageProcessor imageProcessor;
    private final ImageCacheManager imageCacheManager;

    public ImageProcessorService(FileCacheManagerService fileCacheManager) {
        this.imageProcessor = new ImageProcessor();
        this.imageCacheManager = new ImageCacheManager(fileCacheManager);
    }

    public void addWatermark(File inputFile, File watermarkFile, String cacheKey) throws IOException {
        BufferedImage inputImage = imageProcessor.fileToBufferedImage(inputFile);
        if (inputImage == null) {
            LOGGER.error("Failed to read input image: " + inputFile.getPath());
        }

        BufferedImage watermarkedImage = imageProcessor.addWatermark(inputImage, watermarkFile);
        imageCacheManager.cacheBufferedImage(watermarkedImage, FileUtils.getFormat(cacheKey), cacheKey);
        LOGGER.info("Watermark added and image cached with key: " + cacheKey);
    }

    public void changeImageFormat(File inputFile, String format, String cacheKey) throws IOException {
        BufferedImage inputImage = imageProcessor.fileToBufferedImage(inputFile);
        if (inputImage == null) {
            LOGGER.error("Failed to read input image: " + inputFile.getPath());
        }

        imageCacheManager.cacheBufferedImage(inputImage, format, cacheKey);
        LOGGER.info("Image format changed and cached with key: " + cacheKey);
    }

    public void resizeImage(File inputFile, String cacheKey, int width, int height) throws IOException {
        BufferedImage inputImage = imageProcessor.fileToBufferedImage(inputFile);
        if (inputImage == null) {
            LOGGER.error("Failed to read input image: " + inputFile.getPath());
        }
        BufferedImage modifiedImage = imageProcessor.resizeImage(inputImage, width, height);

        imageCacheManager.cacheBufferedImage(modifiedImage, FileUtils.getFormat(cacheKey), cacheKey);
        LOGGER.info("Image resized and cached with key: " + cacheKey);
    }

    public ImageProcessor getImageProcessor() {
        return this.imageProcessor;
    }

    public ImageCacheManager getImageCacheManager() {
        return this.imageCacheManager;
    }
}
