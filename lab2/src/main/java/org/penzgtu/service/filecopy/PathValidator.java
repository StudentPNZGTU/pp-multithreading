package org.penzgtu.service.filecopy;

import org.penzgtu.utils.FileUtils;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PathValidator {

    public List<Path> parseAndValidatePaths(String input) {
        String[] paths = input.matches("[\\n\\r]") ? input.split("\n") : input.split("\\s+");
        List<Path> validPaths = new ArrayList<>();

        for (String pathStr : paths) {
            Path path = Path.of(pathStr.trim());
            if (FileUtils.fileExists(path) || FileUtils.directoryExists(path)) {
                validPaths.add(path);
            }
        }
        return validPaths;
    }

    public boolean filesExist(String paths) {
        for (Path path : parseAndValidatePaths(paths)) {
            if (!FileUtils.fileExists(path)) {
                return false;
            }
        }
        return true;
    }
} 