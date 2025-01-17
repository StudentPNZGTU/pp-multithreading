package org.penzgtu.service.filecopy;

import org.penzgtu.service.ThreadManager;

import java.nio.file.Path;
import java.util.List;

public class FileCopyManagerService {
    private final FileCopier fileCopier;
    private final PathValidator pathValidator;
    private final ThreadManager threadManager;

    public FileCopyManagerService() {
        this.threadManager = new ThreadManager();
        this.fileCopier = new FileCopier(threadManager);
        this.pathValidator = new PathValidator();
    }

    public void copyFiles(List<Path> files, Path targetDir) {
        fileCopier.copyFiles(files, targetDir);
    }

    public List<Path> parseAndValidatePaths(String input) {
        return pathValidator.parseAndValidatePaths(input);
    }

    public boolean filesExist(String paths) {
        return pathValidator.filesExist(paths);
    }

    public void shutdown() {
        threadManager.shutdown();
    }
}

