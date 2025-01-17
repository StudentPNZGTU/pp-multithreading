package org.penzgtu.view.menu.imageprocessor.strategy;

import io.bretty.console.view.ActionView;
import io.bretty.console.view.Validator;
import org.penzgtu.service.imageprocessor.ImageProcessorService;
import org.penzgtu.utils.FileUtils;
import org.penzgtu.view.swing.FolderViewer;
import org.penzgtu.view.swing.ImageViewer;
import org.penzgtu.view.menu.imageprocessor.ActionImage;

import java.nio.file.Path;

public class DefaultActionImageProcessor extends ActionView implements ActionImage, ActionImageStrategy {

    protected String defaultPathInputFile;
    protected String defaultPathOutputFile;
    protected final ImageProcessorService imageProcessorService;

    public DefaultActionImageProcessor(String runningTitle, String nameInParentMenu,
                                       ImageProcessorService imageProcessorService) {
        super(runningTitle, nameInParentMenu);
        this.imageProcessorService = imageProcessorService;
    }

    @Override
    public void executeCustomAction() {
        inputPaths();
        execute();
        saveImageToCache();
    }
    @Override
    public void execute() {
        // write the basic logic
    }

    public void inputPaths() {
        runFolderViewer();
        setDefaultPathInputFile(this.prompt("Введите путь изображения: ", String.class,
                input -> FileUtils.fileExists(Path.of(input))));
        runImageViewer(this.defaultPathInputFile);

        runFolderViewer();
        setDefaultPathOutputFile(this.prompt("Введите директорию нового файла c /названием.example: ", String.class));
    }

    public void saveImageToCache() {
        Validator<String> inputValidator = input -> input.equals("y") || input.equals("n");

        String choice = this.prompt("Сохранить изображение (y / n) ? ", String.class, inputValidator);
        if (choice.equals("y")) {
            this.imageProcessorService.getImageCacheManager().createImageFromCache(this.defaultPathOutputFile);
            runImageViewer(this.defaultPathOutputFile);
        } else {
            this.println("canceled.");
        }
    }

    @Override
    public void runImageViewer(String pathToImage) {
        ImageViewer.runImage(pathToImage);
    }

    @Override
    public void runFolderViewer() {
        FolderViewer.runFolderViewer();
    }

    @Override
    public void setDefaultPathInputFile(String pathInputFile) {
        this.defaultPathInputFile = pathInputFile;
    }

    @Override
    public void setDefaultPathOutputFile(String pathOutputFile) {
        this.defaultPathOutputFile = pathOutputFile;
    }
}
