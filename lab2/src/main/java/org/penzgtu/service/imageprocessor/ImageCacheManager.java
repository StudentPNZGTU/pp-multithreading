package org.penzgtu.service.imageprocessor;

import org.penzgtu.service.cache.FileCacheManagerService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class ImageCacheManager {
    private static final Logger LOGGER = LogManager.getLogger(ImageCacheManager.class);
    private final FileCacheManagerService fileCacheManager;

    public ImageCacheManager(FileCacheManagerService fileCacheManager) {
        this.fileCacheManager = fileCacheManager;
    }

    public void cacheBufferedImage(BufferedImage bufferedImage, String format, String cacheKey) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            boolean success = ImageIO.write(bufferedImage, format, bos);
            if (!success) {
                throw new IOException("Failed to write image to byte array");
            }
            fileCacheManager.cacheFile(cacheKey, bos.toByteArray());
            LOGGER.info("Image cached with key: " + cacheKey);
        }
    }

    public void createImageFromCache(String cacheKey) {
        fileCacheManager.createFileFromCache(cacheKey);
    }
} 