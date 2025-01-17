package org.penzgtu.service.cache;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class FileCacheManagerService {
    private final ConcurrentHashMap<String, byte[]> fileCache = new ConcurrentHashMap<>();
    private final ReentrantLock lock = new ReentrantLock();
    private static final Logger LOGGER = LogManager.getLogger(FileCacheManagerService.class);

    public ConcurrentHashMap<String, byte[]> getFileCache() {
        return fileCache;
    }

    public byte[] getCachedFile(String cacheKey) {
        return fileCache.get(cacheKey);
    }

    public void cacheFile(String cacheKey, byte[] data) {
        lock.lock();
        try {
            fileCache.put(cacheKey, data);
            LOGGER.info("Added cache image in Cache Service with key: " + cacheKey);
        } finally {
            lock.unlock();
        }
    }

    public void createFileFromCache(String cacheKey) {
        byte[] cachedData = getCachedFile(cacheKey);
        if (cachedData == null) {
            LOGGER.warn("No data found in cache for key: " + cacheKey);
            return;
        }
        try (FileOutputStream fos = new FileOutputStream(cacheKey)) {
            fos.write(cachedData);
            LOGGER.info("File successfully written from cache with key: " + cacheKey);
        } catch (IOException e) {
            LOGGER.error("Error writing file from cache with key: " + cacheKey, e);
        }
    }

    public void createAllFilesFromCache() {
        for (String cacheKey : fileCache.keySet()) {
            createFileFromCache(cacheKey);
        }
    }

    public void clearCache() {
        fileCache.clear();
        LOGGER.info("Cache cleared.");
    }
} 