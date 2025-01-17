package org.penzgtu.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class FileUtils {

    public static String extractFileName(String path) {
        return Paths.get(path).getFileName().toString();
    }

    public static String getFormat(String path) {
        String fileName = extractFileName(path);
        String extension = "";

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1);
        }
        return extension;
    }

    public static boolean directoryExists(Path path) {
        return Files.isDirectory(path);
    }

    public static boolean fileExists(Path path) {
        return Files.isRegularFile(path);
    }
}
