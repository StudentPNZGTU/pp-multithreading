package org.penzgtu.view.menu;

import io.bretty.console.view.ActionView;
import org.penzgtu.service.filecopy.FileCopyManagerService;
import org.penzgtu.utils.FileUtils;
import org.penzgtu.view.swing.FolderViewer;

import java.nio.file.Path;
import java.util.List;

public class FileCopyConsoleManager extends ActionView {

    private final FileCopyManagerService fileCopyManagerService;

    public FileCopyConsoleManager(String runningTitle, String nameInParentMenu,
                                  FileCopyManagerService fileCopyManagerService) {
        super(runningTitle, nameInParentMenu);
        this.fileCopyManagerService = fileCopyManagerService;
    }

    @Override
    public void executeCustomAction() {

        FolderViewer.runFolderViewer();
        String fileOrFiles = this.prompt("Введите файл(ы): ", String.class,
                fileCopyManagerService::filesExist);
        FolderViewer.runFolderViewer();
        String pathToCP = this.prompt("Введите директорию куда вставить файл(ы): ", String.class,
                path -> FileUtils.directoryExists(Path.of(path)));

        List<Path> pathList = fileCopyManagerService.parseAndValidatePaths(fileOrFiles);

        fileCopyManagerService.copyFiles(pathList, Path.of(pathToCP));
        this.println("Copy files: " + pathList.size());
    }
}
